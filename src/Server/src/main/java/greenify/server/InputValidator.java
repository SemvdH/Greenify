package greenify.server;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputValidator {

    private static final List<InputItem> inputItems = Arrays.asList(
            new InputItem("input_location", false, "Chicago"),
            new InputItem("input_location_mode", false, "1"),
            new InputItem("input_size", false, "3"),
            new InputItem("input_income", false, "3000"),
            new InputItem("input_population", false, "1"),
            new InputItem("input_changed", false, "0"),
            new InputItem("input_footprint_household_adults", false, "0"),
            new InputItem("input_footprint_household_children", false, "0"),
            new InputItem("input_footprint_transportation_num_vehicles", false, "10"),
            new InputItem("input_footprint_transportation_miles1", false, "16100", false),
            new InputItem("input_footprint_transportation_mpg1", false, "22", false),
            new InputItem("input_footprint_transportation_fuel1", false, "0", false),
            new InputItem("input_footprint_transportation_miles2", false, "0", false),
            new InputItem("input_footprint_transportation_fuel2", false, "0", false),
            new InputItem("input_footprint_transportation_mpg2", false, "0", false),
            new InputItem("input_footprint_transportation_miles3", false, "0", false),
            new InputItem("input_footprint_transportation_fuel3", false, "0", false),
            new InputItem("input_footprint_transportation_mpg3", false, "0", false),
            new InputItem("input_footprint_transportation_miles4", false, "0", false),
            new InputItem("input_footprint_transportation_fuel4", false, "0", false),
            new InputItem("input_footprint_transportation_mpg4", false, "0", false),
            new InputItem("input_footprint_transportation_miles5", false, "0", false),
            new InputItem("input_footprint_transportation_fuel5", false, "0", false),
            new InputItem("input_footprint_transportation_mpg5", false, "0", false),
            new InputItem("input_footprint_transportation_miles6", false, "0", false),
            new InputItem("input_footprint_transportation_fuel6", false, "0", false),
            new InputItem("input_footprint_transportation_mpg6", false, "0", false),
            new InputItem("input_footprint_transportation_miles7", false, "0", false),
            new InputItem("input_footprint_transportation_fuel7", false, "0", false),
            new InputItem("input_footprint_transportation_mpg7", false, "0", false),
            new InputItem("input_footprint_transportation_miles8", false, "0", false),
            new InputItem("input_footprint_transportation_fuel8", false, "0", false),
            new InputItem("input_footprint_transportation_mpg8", false, "0", false),
            new InputItem("input_footprint_transportation_miles9", false, "0", false),
            new InputItem("input_footprint_transportation_fuel9", false, "0", false),
            new InputItem("input_footprint_transportation_mpg9", false, "0", false),
            new InputItem("input_footprint_transportation_miles10", false, "0", false),
            new InputItem("input_footprint_transportation_fuel10", false, "0", false),
            new InputItem("input_footprint_transportation_mpg10", false, "0", false),
            new InputItem("input_footprint_transportation_groundtype", false, "0"),
            new InputItem("input_footprint_transportation_publictrans", false, "436"),
            new InputItem("input_footprint_transportation_bus", false, "174"),
            new InputItem("input_footprint_transportation_transit", false, "131"),
            new InputItem("input_footprint_transportation_commuter", false, "87"),
            new InputItem("input_footprint_transportation_intercity", false, "44"),
            new InputItem("input_footprint_transportation_airtype", false, "0"),
            new InputItem("input_footprint_transportation_airtotal", false, "6"),
            new InputItem("input_footprint_transportation_airshort", false, "3"),
            new InputItem("input_footprint_transportation_airmedium", false, "3"),
            new InputItem("input_footprint_transportation_airlong", false, "0"),
            new InputItem("input_footprint_transportation_airextended", false, "0"),
            new InputItem("input_footprint_housing_cdd", false, "40000"),
            new InputItem("input_footprint_housing_hdd", false, "40000"),
            new InputItem("input_footprint_housing_electricity_type", false, "0"),
            new InputItem("input_footprint_housing_electricity_dollars", false, "1200"),
            new InputItem("input_footprint_housing_electricity_kwh", false, "12632"),
            new InputItem("input_footprint_housing_cleanpercent", false, "0"),
            new InputItem("input_footprint_housing_naturalgas_type", false, "0"),
            new InputItem("input_footprint_housing_naturalgas_dollars", false, "600"),
            new InputItem("input_footprint_housing_naturalgas_therms", false, "472"),
            new InputItem("input_footprint_housing_naturalgas_cuft", false, "472444"),
            new InputItem("input_footprint_housing_heatingoil_type", false, "0"),
            new InputItem("input_footprint_housing_heatingoil_dollars", false, "220"),
            new InputItem("input_footprint_housing_heatingoil_gallons", false, "73"),
            new InputItem("input_footprint_housing_heatingoil_dollars_per_gallon", false, "40000"),
            new InputItem("input_footprint_housing_squarefeet", false, "1850"),
            new InputItem("input_footprint_housing_watersewage", false, "100"),
            new InputItem("input_footprint_housing_gco2_per_kwh", false, "0"),
            new InputItem("input_footprint_shopping_food_meatfisheggs_default", true, "2.4"),
            new InputItem("input_footprint_shopping_food_meat_beefpork_default", true, "1.1"),
            new InputItem("input_footprint_shopping_food_meat_poultry_default", true, "0.7"),
            new InputItem("input_footprint_shopping_food_meat_fish_default", true, "0.3"),
            new InputItem("input_footprint_shopping_food_meat_other_default", true, "0.3"),
            new InputItem("input_footprint_shopping_food_fruitvegetables_default", true, "3.5"),
            new InputItem("input_footprint_shopping_food_dairy_default", true, "2.2"),
            new InputItem("input_footprint_shopping_food_cereals_default", true, "4.1"),
            new InputItem("input_footprint_shopping_food_otherfood_default", true, "3.4"),
            new InputItem("input_footprint_shopping_food_meattype", true, "0"),
            new InputItem("input_footprint_shopping_food_meatfisheggs", true, "2.4"),
            new InputItem("input_footprint_shopping_food_meat_beefpork", true, "2.4"),
            new InputItem("input_footprint_shopping_food_meat_poultry", true, "2.4"),
            new InputItem("input_footprint_shopping_food_meat_fish", true, "2.4"),
            new InputItem("input_footprint_shopping_food_meat_other", true, "2.4"),
            new InputItem("input_footprint_shopping_food_cereals", true, "4.1"),
            new InputItem("input_footprint_shopping_food_dairy", true, "2.2"),
            new InputItem("input_footprint_shopping_food_fruitvegetables", true, "3.5"),
            new InputItem("input_footprint_shopping_food_otherfood", true, "3.4"),
            new InputItem("input_footprint_shopping_goods_default_furnitureappliances",
                    false, "1310"),
            new InputItem("input_footprint_shopping_goods_default_clothing", false, "1310"),
            new InputItem("input_footprint_shopping_goods_default_other_entertainment",
                    false, "1310"),
            new InputItem("input_footprint_shopping_goods_default_other_office", false, "1310"),
            new InputItem(
                    "input_footprint_shopping_goods_default_other_personalcare", false, "1310"),
            new InputItem("input_footprint_shopping_goods_default_other_autoparts",
                    false, "1310"),
            new InputItem("input_footprint_shopping_goods_default_other_medical", false, "1310"),
            new InputItem("input_footprint_shopping_goods_type", false, "1310"),
            new InputItem("input_footprint_shopping_goods_total", false, "1310"),
            new InputItem("input_footprint_shopping_goods_furnitureappliances", false, "362"),
            new InputItem("input_footprint_shopping_goods_clothing", false, "391"),
            new InputItem("input_footprint_shopping_goods_other_type", false, "0"),
            new InputItem("input_footprint_shopping_goods_other_total", false, "1311"),
            new InputItem("input_footprint_shopping_goods_other_entertainment", false, "200"),
            new InputItem("input_footprint_shopping_goods_other_office", false, "38"),
            new InputItem("input_footprint_shopping_goods_other_personalcare", false, "103"),
            new InputItem("input_footprint_shopping_goods_other_autoparts", false, "45"),
            new InputItem("input_footprint_shopping_goods_other_medical", false, "172"),
            new InputItem("input_footprint_shopping_services_type", false, "0"),
            new InputItem("input_footprint_shopping_services_total", false, "2413"),
            new InputItem("input_footprint_shopping_services_healthcare", false, "841"),
            new InputItem("input_footprint_shopping_services_education", false, "122"),
            new InputItem("input_footprint_shopping_services_communications", false, "163"),
            new InputItem("input_footprint_shopping_services_vehicleservices", false, "180"),
            new InputItem("input_footprint_shopping_services_finance", false, "566"),
            new InputItem("input_footprint_shopping_services_household", false, "28"),
            new InputItem("input_footprint_shopping_services_charity", false, "146"),
            new InputItem("input_footprint_shopping_services_miscservices", false, "114"),
            new InputItem("internal_state_abbreviation", false, "US")
    );

    /**
     * The method checks whether the id is valid or not.
     * @param inputName the name of input
     * @return true or false
     */
    public static Boolean isValidItem(String inputName) {
        return inputItems.stream().anyMatch(i -> i.getName().equals(inputName));
    }

    /**
     * The method checks whether the item value is valid or not.
     * @param inputName the name of input
     * @param value the value of item
     * @return true or false
     */
    public static boolean isValidItemValue(String inputName, String value) {
        InputItem item = null;
        for (InputItem inputItem : inputItems) {
            if (inputItem.getName().equals(inputName)) {
                item = inputItem;
            }
        }
        if (item.getFloat()) {
            try {
                Float.parseFloat(value);
            } catch (NumberFormatException | NullPointerException nfe) {
                return false;
            }
            return true;
        } else {
            try {
                Integer.parseInt(value);
            } catch (NumberFormatException | NullPointerException nfe) {
                return false;
            }
            return true;
        }
    }

    /**
     * This method gets default values.
     * @return the map of default values
     */
    public static Map<String, String> getDefaultValues() {
        Map<String, String> map = new HashMap<String, String>() { };
        for (InputItem inputItem : inputItems) {
            map.put(inputItem.getName(), inputItem.getDefaultValue());
        }
        return map;
    }

    /**
     * This method gets extra values.
     * @return the map of default values
     */
    public static Map<String, String> getExtraValues() {
        Map<String, String> map = new HashMap<String, String>() { };
        map.put("vegan", "0");
        map.put("local_produce", "0");
        map.put("bike", "0");
        map.put("public_transport", "0");
        map.put("temperature", "0");
        map.put("solar_panels", "0");
        return map;
    }
}