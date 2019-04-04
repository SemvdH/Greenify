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
        System.out.println(Float.parseFloat(result));
        return Float.parseFloat(result);
    }

    /**
     * The method calculates a users footprint.
     * @param user the user
     * @return the footprint of the user
     */
    public Float calculateFootprint(User user) {
        Float footprint =  invokeExternalService(user.getFootPrintInputs());
        addExtras(user);
        return footprint;
    }

    /**
     * This method adds extra input to the user.
     * @param user name of the user
     */
    public void addExtras(User user) {
        if (user.getExtraInputs().get("local_produce")) {
            user.setFootPrint(user.getFootPrint() - 2);
        } else if (user.getExtraInputs().get("bike")) {
            user.setFootPrint(user.getFootPrint() - 2);
        } else if (user.getExtraInputs().get("temperature")) {
            user.setFootPrint(user.getFootPrint() - 2);
        } else if (user.getExtraInputs().get("solar_panels")) {
            user.setFootPrint(user.getFootPrint() - 2);
        }
    }
}

