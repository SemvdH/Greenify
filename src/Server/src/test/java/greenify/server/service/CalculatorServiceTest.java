package greenify.server.service;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;

import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

import greenify.server.data.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
public class CalculatorServiceTest {

    @TestConfiguration
    static class CalculatorServiceTestConfiguration {

        RestTemplate restTemplate = new RestTemplate();

        @Bean
        public CalculatorService calculatorService() {
            return new CalculatorService();
        }

        @Bean
        public RestTemplate restTemplate() {
            return restTemplate;
        }
    }

    @Autowired
    private CalculatorService calculatorService;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    @Before
    public void init() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void calculateFootprintTest() throws URISyntaxException {
        Map<String,String> map = new HashMap<String, String>() {{
                put("input_location_mode", "1");
                put("input_location", "48001");
                put("input_income", "1");
            }
        };
        User user = new User(1L,"greenify", "password");
        user.setFootPrintInputs(map);
        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI("https://apis.berkeley.edu/coolclimate/footprint?"
                        + "input_location=48001&input_location_mode=1&input_income=1")))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header("app_id", "a98272e3"))
                .andExpect(header("app_key", "b9167c4918cb2b3143614b595065d83b"))
                .andRespond(withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                        + "<response>\n"
                        + "   <result_motor_vehicles_direct>5.0765</result_motor_vehicles_direct>\n"
                        + "   <result_motor_vehicles_indirect>1.167595"
                        + "</result_motor_vehicles_indirect>\n"
                        + "   <result_goods_total>2.481474</result_goods_total>\n"
                        + "   <result_services_total>2.478352</result_services_total>\n"
                        + "   <result_grand_total>19.259982</result_grand_total>\n"
                        + "</response>")
            );
        Float footPrint = calculatorService.calculateFootprint(user);
        mockServer.verify();
        Assert.assertEquals(new Float(19.259982), footPrint);
    }

    @Test
    public void invokeExternalServiceTest() {
        CalculatorService service = new CalculatorService();
        service.restTemplate = new RestTemplate();
        Map<String,String> map = new HashMap<String, String>() {{
                put("input_location", "Chicago");
                put("input_location_mode", "1");
                put("input_size", "1");
                put("input_income", "40000");
                put("input_population", "1");
                put("input_changed", "0");
                put("input_footprint_household_adults", "1");
                put("input_footprint_household_children", "0");
                put("input_footprint_transportation_num_vehicles", "1");
                put("input_footprint_transportation_miles1", "16100");
                put("input_footprint_transportation_mpg1", "6");
                put("input_footprint_transportation_fuel1", "2");
                put("input_footprint_transportation_miles2", "13200");
                put("input_footprint_transportation_fuel2", "0");
                put("input_footprint_transportation_mpg2", "22");
                put("input_footprint_transportation_miles3", "13200");
                put("input_footprint_transportation_fuel3", "0");
                put("input_footprint_transportation_mpg3", "22");
                put("input_footprint_transportation_miles4", "13200");
                put("input_footprint_transportation_fuel4", "0");
                put("input_footprint_transportation_mpg4", "22");
                put("input_footprint_transportation_miles5", "13200");
                put("input_footprint_transportation_fuel5", "0");
                put("input_footprint_transportation_mpg5", "22");
                put("input_footprint_transportation_miles6", "13200");
                put("input_footprint_transportation_fuel6", "0");
                put("input_footprint_transportation_mpg6", "22");
                put("input_footprint_transportation_miles7", "13200");
                put("input_footprint_transportation_fuel7", "0");
                put("input_footprint_transportation_mpg7", "22");
                put("input_footprint_transportation_miles8", "13200");
                put("input_footprint_transportation_fuel8", "0");
                put("input_footprint_transportation_mpg8", "22");
                put("input_footprint_transportation_miles9", "13200");
                put("input_footprint_transportation_fuel9", "0");
                put("input_footprint_transportation_mpg9", "22");
                put("input_footprint_transportation_miles10", "13200");
                put("input_footprint_transportation_fuel10", "0");
                put("input_footprint_transportation_mpg10", "22");
                put("input_footprint_transportation_groundtype", "436");
                put("input_footprint_transportation_publictrans", "436");
                put("input_footprint_transportation_bus", "436");
                put("input_footprint_transportation_transit", "436");
                put("input_footprint_transportation_commuter", "436");
                put("input_footprint_transportation_intercity", "436");
                put("input_footprint_transportation_airtype", "3900");
                put("input_footprint_transportation_airtotal", "3900");
                put("input_footprint_transportation_airshort", "3900");
                put("input_footprint_transportation_airmedium", "3900");
                put("input_footprint_transportation_airlong", "3900");
                put("input_footprint_transportation_airextended", "3900");
                put("input_footprint_housing_cdd", "40000");
                put("input_footprint_housing_hdd", "40000");
                put("input_footprint_housing_electricity_type", "40000");
                put("input_footprint_housing_electrivity_dollars", "40000");
                put("input_footprint_housing_electricity_kwh", "12632");
                put("input_footprint_housing_cleanpercent", "0");
                put("input_footprint_housing_naturalgas_type", "1");
                put("input_footprint_housing_naturalgas_dollars", "40000");
                put("input_footprint_housing_naturalgas_therms", "472");
                put("input_footprint_housing_naturalgas_cuft", "40000");
                put("input_footprint_housing_heatingoil_type", "40000");
                put("input_footprint_housing_heatingoil_dollars", "40000");
                put("input_footprint_housing_heatingoil_gallons", "73");
                put("input_footprint_housing_heatingoil_dollars_per_gallon", "40000");
                put("input_footprint_housing_squarefeet", "1850");
                put("input_footprint_housing_watersewage", "100");
                put("input_footprint_housing_gco2_per_kwh", "40000");
                put("input_footprint_shopping_food_meatfisheggs_default", "40000");
                put("input_footprint_shopping_food_meat_beefpork_default", "40000");
                put("input_footprint_shopping_food_meat_poultry_default", "40000");
                put("input_footprint_shopping_food_meat_fish_default", "40000");
                put("input_footprint_shopping_food_meat_other_default", "40000");
                put("input_footprint_shopping_food_fruitvegetables_default", "40000");
                put("input_footprint_shopping_food_dairy_default", "4.2");
                put("input_footprint_shopping_food_cereals_default", "40000");
                put("input_footprint_shopping_food_otherfood_default", "40000");
                put("input_footprint_shopping_food_meattype", "40000");
                put("input_footprint_shopping_food_meatfisheggs", "2.4");
                put("input_footprint_shopping_food_meat_beefpork", "2.4");
                put("input_footprint_shopping_food_meat_poultry", "2.4");
                put("input_footprint_shopping_food_meat_fish", "2.4");
                put("input_footprint_shopping_food_meat_other", "2.4");
                put("input_footprint_shopping_food_cereals", "4.1");
                put("input_footprint_shopping_food_dairy", "2.2");
                put("input_footprint_shopping_food_fruitvegetables", "3.5");
                put("input_footprint_shopping_food_otherfood", "3.4");
                put("input_footprint_shopping_goods_default_furnitureappliances", "1310");
                put("input_footprint_shopping_goods_default_clothing", "1310");
                put("input_footprint_shopping_goods_default_other_entertainment", "1310");
                put("input_footprint_shopping_goods_default_other_office", "1310");
                put("input_footprint_shopping_goods_default_other_personalcare", "1310");
                put("input_footprint_shopping_goods_default_other_autoparts", "1310");
                put("input_footprint_shopping_goods_default_other_medical", "1310");
                put("input_footprint_shopping_goods_type", "1310");
                put("input_footprint_shopping_goods_total", "1310");
                put("input_footprint_shopping_goods_furnitureappliances", "1310");
                put("input_footprint_shopping_goods_clothing", "1310");
                put("input_footprint_shopping_goods_other_type", "1310");
                put("input_footprint_shopping_goods_other_total", "1310");
                put("input_footprint_shopping_goods_other_entertainment", "1310");
                put("input_footprint_shopping_goods_other_office", "1310");
                put("input_footprint_shopping_goods_other_personalcare", "1310");
                put("input_footprint_shopping_goods_other_autoparts", "1310");
                put("input_footprint_shopping_goods_other_medical", "1310");
                put("input_footprint_shopping_services_type", "1310");
                put("input_footprint_shopping_services_total", "1310");
                put("input_footprint_shopping_services_healthcare", "1310");
                put("input_footprint_shopping_services_education", "1310");
                put("input_footprint_shopping_services_communications", "1310");
                put("input_footprint_shopping_services_vehicleservices", "1310");
                put("input_footprint_shopping_services_finance", "1310");
                put("input_footprint_shopping_services_household", "1310");
                put("input_footprint_shopping_services_charity", "1310");
                put("input_footprint_shopping_services_miscservices", "1310");
                put("internal_state_abbreviation", "US");
                put("input_footprint_shopping_services_total", "2413");
            }
        };
        Float footPrint = service.invokeExternalService(map);
        Assert.assertEquals(new Float(11421.537), footPrint);
    }
}
