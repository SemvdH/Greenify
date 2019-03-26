import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import greenify.server.InputValidator;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class InputValidatorTest {

    @Test
    public void validItemIdTest() {
        new InputValidator();
        assertEquals(InputValidator.isValidItem("transportation_num_vehicles"), true);
        assertEquals(InputValidator.isValidItem("test"), false);
    }

    @Test
    public void validItemValueTest() {
        assertTrue(InputValidator
                .isValidItemValue("transportation_num_vehicles", "4"));
        assertFalse(InputValidator
                .isValidItemValue("transportation_num_vehicles", "3.5"));
        assertFalse(InputValidator.isValidItemValue("food_grains", "hello"));
        assertTrue(InputValidator.isValidItemValue("food_grains", "5"));
        assertTrue(InputValidator.isValidItemValue("food_grains", "3.5"));
    }

    @Test
    public void getDefaultValuesTest() {
        Map<String, String> map = new HashMap<String, String>() {{
                put("input_size", "1");
                put("input_income", "40000");
                put("transportation_num_vehicles", "1");
                put("transportation_miles1", "16100");
                put("transportation_fuels1", "2");
                put("transportation_mpg1", null);
                put("transportation_miles2", "13200");
                put("transportation_fuels2", "0");
                put("transportation_mpg2", "22");
                put("transportation_publicTrans", "436");
                put("transportation_air", "3900");
                put("housing_electricity_kwh_year", "12632");
                put("housing_cleanPercent", "0");
                put("housing_naturalGas_therms_year", "472");
                put("housing_heatingOil_gallons_year", "73");
                put("housing_square_feet", "1850");
                put("housing_water_sewage", "100");
                put("food_meat_fish_eggs", "2.4");
                put("food_grains", "4.1");
                put("food_dairy", "2.2");
                put("food_fruit_vegetables", "3.5");
                put("food_snacks_drinks", "3.4");
                put("shopping_goods", "1310");
                put("shopping_services", "2413");
            }
        };
        assertEquals(InputValidator.getDefaultValues(), map);
    }
}
