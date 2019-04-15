package greenify.server.service;

import greenify.server.data.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class CalculatorService {
    @Autowired
    RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Bean
    RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    /**
     * This method invokes the external service that calculates a footprint.
     * @param map used variables to calculate a footprint
     * @return a footprint
     */
    public Float invokeExternalService(Map<String, String> map) {
        /*
         * curl -X GET "https://apis.berkeley.edu/coolclimate/footprint-sandbox?input_location_mode=1
         * &input_location=48001&input_income=1&input_size=0&input_footprint_transportation_miles1=3
         * &input_footprint_transportation_mpg1=5&input_footprint_transportation_fuel1=0"
         * -H "accept: application/json" -H "app_id: a98272e3"
         * -H "app_key: b9167c4918cb2b3143614b595065d83b"
         */
        HttpHeaders headers = new HttpHeaders();
        headers.set("accept", MediaType.APPLICATION_JSON_VALUE);
        headers.set("app_id", "a98272e3");
        headers.set("app_key", "b9167c4918cb2b3143614b595065d83b");
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl("https://apis.berkeley.edu/coolclimate/footprint");
        for (String inputId : map.keySet()) {
            builder = builder.queryParam(inputId, map.get(inputId));
        }
        ResponseEntity<String> response = restTemplate
                .exchange(builder.build().encode().toUri(), HttpMethod.GET,
                entity, String.class);
        logger.info(response.getStatusCode().toString());
        logger.info(response.getBody());
        String result = response.getBody().substring(Objects.requireNonNull(response.getBody())
                .indexOf("<result_grand_total>")
                + 20, response.getBody().indexOf("</result_grand_total>"));
        // to do: in not HTTP 200 or exception case throws exception
        return Float.parseFloat(result);
    }

    /**
     * The method calculates a users footprint.
     * @param user the user
     * @return the footprint of the user
     */
    public Float calculateFootprint(User user) {
        addExtras(user);
        Float footprint =  invokeExternalService(user.getFootPrintInputs());
        footprint = footprint - (float) (Float.parseFloat(user
                .getExtraInputs().get("solar_panels")) * 1.2);
        footprint = footprint - Float.parseFloat(user
                .getExtraInputs().get("temperature")) / 4;
        footprint = footprint - (float) (Float.parseFloat(user
                .getExtraInputs().get("local_produce")) * 0.1);
        return footprint;
    }

    /**
     * This method adds extra input to the user.
     * @param user name of the user
     */
    public void addExtras(User user) {
        Map<String, String> inputs = user.getFootPrintInputs();
        Float netPublic = Float.parseFloat(user.getFootPrintInputs()
                .get("input_footprint_transportation_publictrans"))
                + Float.parseFloat(user.getExtraInputs().get("public_transport"));
        Float netCar = Float.parseFloat(user.getFootPrintInputs()
                .get("input_footprint_transportation_miles1"))
                - Float.parseFloat(user.getExtraInputs().get("public_transport"))
                - Float.parseFloat(user.getExtraInputs().get("bike"));
        Float netVegan = Float.parseFloat(user.getFootPrintInputs()
                .get("input_footprint_shopping_food_fruitvegetables"))
                + Float.parseFloat(user.getExtraInputs().get("vegan"));
        Float netShopping = Float.parseFloat(user.getFootPrintInputs()
                .get("input_footprint_shopping_goods_total"))
                - Float.parseFloat(user.getExtraInputs().get("local_produce")) * 100;
        inputs.put("input_footprint_transportation_publictrans", netPublic + "");
        inputs.put("input_footprint_transportation_miles1", netCar + "");
        inputs.put("input_footprint_shopping_food_fruitvegetables", netVegan + "");
        inputs.put("input_footprint_shopping_goods_total", netShopping + "");
        user.setFootPrintInputs(inputs);
    }

    /**
     * Gets the result of the CO2-calculation from the CoolClimate website
     * @param map results that the user filled in
     * @return the results from the website.
     */
    public Map<String, String> getResults(Map<String, String> map) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("accept", MediaType.APPLICATION_JSON_VALUE);
        headers.set("app_id", "a98272e3");
        headers.set("app_key", "b9167c4918cb2b3143614b595065d83b");
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl("https://apis.berkeley.edu/coolclimate/footprint");
        for (String inputId : map.keySet()) {
            builder = builder.queryParam(inputId, map.get(inputId));
        }
        ResponseEntity<String> response = restTemplate
                .exchange(builder.build().encode().toUri(), HttpMethod.GET,
                        entity, String.class);
        String transport = response.getBody().substring(Objects.requireNonNull(response.getBody())
                .indexOf("<result_transport_total>")
                + 24, response.getBody().indexOf("</result_transport_total>"));
        String housing = response.getBody().substring(Objects.requireNonNull(response.getBody())
                .indexOf("<result_housing_total>")
                + 22, response.getBody().indexOf("</result_housing_total>"));
        String food = response.getBody().substring(Objects.requireNonNull(response.getBody())
                .indexOf("<result_food_total>")
                + 19, response.getBody().indexOf("</result_food_total>"));
        String goods = response.getBody().substring(Objects.requireNonNull(response.getBody())
                .indexOf("<result_goods_total>")
                + 20, response.getBody().indexOf("</result_goods_total>"));
        String services = response.getBody().substring(Objects.requireNonNull(response.getBody())
                .indexOf("<result_services_total>")
                + 23, response.getBody().indexOf("</result_services_total>"));
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("transport", transport);
        resultMap.put("housing", housing);
        resultMap.put("food", food);
        resultMap.put("goods", goods);
        resultMap.put("services", services);
        return resultMap;
    }
}

