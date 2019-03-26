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
                //requestTo(new URI("https://apis.berkeley.edu/coolclimate/footprint-sandbox")))
                requestTo(new URI("https://apis.berkeley.edu/coolclimate/footprint-sandbox?"
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
                put("input_location_mode", "1");
                put("input_location", "48001");
                put("input_income", "1");
                put("input_size", "0");
                put("input_footprint_transportation_miles1", "3");
                put("input_footprint_transportation_mpg1", "5");
                put("input_footprint_transportation_fuel1", "0");
            }
        };
        Float footPrint = service.invokeExternalService(map);
        Assert.assertEquals(new Float(12.743548), footPrint);
    }
}
