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
        assertEquals(InputValidator.isValidItem(
                "input_footprint_shopping_food_dairy_default"), true);
        assertEquals(InputValidator.isValidItem("test"), false);
    }

    @Test
    public void validItemValueTest() {
        assertTrue(InputValidator
                .isValidItemValue("input_footprint_transportation_num_vehicles", "4"));
        assertFalse(InputValidator
                .isValidItemValue("input_footprint_transportation_num_vehicles", "3.5"));
        assertFalse(InputValidator.isValidItemValue(
                "input_footprint_shopping_food_dairy_default", "hello"));
        assertTrue(InputValidator.isValidItemValue(
                "input_footprint_shopping_food_dairy_default", "5"));
        assertTrue(InputValidator.isValidItemValue(
                "input_footprint_shopping_food_dairy_default", "3.5"));
    }

    @Test
    public void getDefaultValuesTest() {
        Map<String, String> map = new HashMap<String, String>() {{
                put("input_location", "Chicago");
                put("input_location_mode", "1");
                put("input_size", "3");
                put("input_income", "3000");
                put("input_population", "1");
                put("input_changed", "0");
                put("input_footprint_household_adults", "0");
                put("input_footprint_household_children", "0");
                put("input_footprint_transportation_num_vehicles", "1");
                put("input_footprint_transportation_miles1", "16100");
                put("input_footprint_transportation_mpg1", "22");
                put("input_footprint_transportation_fuel1", "0");
                put("input_footprint_transportation_miles2", "0");
                put("input_footprint_transportation_fuel2", "0");
                put("input_footprint_transportation_mpg2", "0");
                put("input_footprint_transportation_miles3", "0");
                put("input_footprint_transportation_fuel3", "0");
                put("input_footprint_transportation_mpg3", "0");
                put("input_footprint_transportation_miles4", "0");
                put("input_footprint_transportation_fuel4", "0");
                put("input_footprint_transportation_mpg4", "0");
                put("input_footprint_transportation_miles5", "0");
                put("input_footprint_transportation_fuel5", "0");
                put("input_footprint_transportation_mpg5", "0");
                put("input_footprint_transportation_miles6", "0");
                put("input_footprint_transportation_fuel6", "0");
                put("input_footprint_transportation_mpg6", "0");
                put("input_footprint_transportation_miles7", "0");
                put("input_footprint_transportation_fuel7", "0");
                put("input_footprint_transportation_mpg7", "0");
                put("input_footprint_transportation_miles8", "0");
                put("input_footprint_transportation_fuel8", "0");
                put("input_footprint_transportation_mpg8", "0");
                put("input_footprint_transportation_miles9", "0");
                put("input_footprint_transportation_fuel9", "0");
                put("input_footprint_transportation_mpg9", "0");
                put("input_footprint_transportation_miles10", "0");
                put("input_footprint_transportation_fuel10", "0");
                put("input_footprint_transportation_mpg10", "0");
                put("input_footprint_transportation_groundtype", "0");
                put("input_footprint_transportation_publictrans", "436");
                put("input_footprint_transportation_bus", "174");
                put("input_footprint_transportation_transit", "131");
                put("input_footprint_transportation_commuter", "87");
                put("input_footprint_transportation_intercity", "44");
                put("input_footprint_transportation_airtype", "0");
                put("input_footprint_transportation_airtotal", "6");
                put("input_footprint_transportation_airshort", "3");
                put("input_footprint_transportation_airmedium", "3");
                put("input_footprint_transportation_airlong", "0");
                put("input_footprint_transportation_airextended", "0");
                put("input_footprint_housing_cdd", "40000");
                put("input_footprint_housing_hdd", "40000");
                put("input_footprint_housing_electricity_type", "0");
                put("input_footprint_housing_electrivity_dollars", "1200");
                put("input_footprint_housing_electricity_kwh", "12632");
                put("input_footprint_housing_cleanpercent", "0");
                put("input_footprint_housing_naturalgas_type", "0");
                put("input_footprint_housing_naturalgas_dollars", "600");
                put("input_footprint_housing_naturalgas_therms", "472");
                put("input_footprint_housing_naturalgas_cuft", "472444");
                put("input_footprint_housing_heatingoil_type", "0");
                put("input_footprint_housing_heatingoil_dollars", "220");
                put("input_footprint_housing_heatingoil_gallons", "73");
                put("input_footprint_housing_heatingoil_dollars_per_gallon", "40000");
                put("input_footprint_housing_squarefeet", "1850");
                put("input_footprint_housing_watersewage", "100");
                put("input_footprint_housing_gco2_per_kwh", "0");
                put("input_footprint_shopping_food_meatfisheggs_default", "2.4");
                put("input_footprint_shopping_food_meat_beefpork_default", "1.1");
                put("input_footprint_shopping_food_meat_poultry_default", "0.7");
                put("input_footprint_shopping_food_meat_fish_default", "0.3");
                put("input_footprint_shopping_food_meat_other_default", "0.3");
                put("input_footprint_shopping_food_fruitvegetables_default", "3.5");
                put("input_footprint_shopping_food_dairy_default", "2.2");
                put("input_footprint_shopping_food_cereals_default", "4.1");
                put("input_footprint_shopping_food_otherfood_default", "3.4");
                put("input_footprint_shopping_food_meattype", "0");
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
                put("input_footprint_shopping_goods_furnitureappliances", "362");
                put("input_footprint_shopping_goods_clothing", "391");
                put("input_footprint_shopping_goods_other_type", "0");
                put("input_footprint_shopping_goods_other_total", "1311");
                put("input_footprint_shopping_goods_other_entertainment", "200");
                put("input_footprint_shopping_goods_other_office", "38");
                put("input_footprint_shopping_goods_other_personalcare", "103");
                put("input_footprint_shopping_goods_other_autoparts", "45");
                put("input_footprint_shopping_goods_other_medical", "172");
                put("input_footprint_shopping_services_type", "0");
                put("input_footprint_shopping_services_total", "2413");
                put("input_footprint_shopping_services_healthcare", "841");
                put("input_footprint_shopping_services_education", "122");
                put("input_footprint_shopping_services_communications", "163");
                put("input_footprint_shopping_services_vehicleservices", "180");
                put("input_footprint_shopping_services_finance", "566");
                put("input_footprint_shopping_services_household", "28");
                put("input_footprint_shopping_services_charity", "146");
                put("input_footprint_shopping_services_miscservices", "114");
                put("internal_state_abbreviation", "US");
            }
        };
        assertTrue(map.size() == InputValidator.getDefaultValues().size());
    }
}
