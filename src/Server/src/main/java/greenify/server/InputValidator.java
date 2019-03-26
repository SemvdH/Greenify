package greenify.server;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class InputValidator {

    private static final List<InputItem> inputItems = Arrays.asList(
        new InputItem("input_size", false, "1"),
        new InputItem("input_income", false, "40000"),
        new InputItem("transportation_num_vehicles", false, "1"),
        new InputItem("transportation_miles1", false, "16100", false),
        new InputItem("transportation_fuels1", false, "2", false),
        new InputItem("transportation_mpg1", false, null, false),
        new InputItem("transportation_miles2", false, "13200", false),
        new InputItem("transportation_fuels2", false, "0", false),
        new InputItem("transportation_mpg2", false, "22", false),
        new InputItem("transportation_publicTrans", false, "436"),
        new InputItem("transportation_air", false, "3900"),
        new InputItem("housing_electricity_kwh_year", false, "12632"),
        new InputItem("housing_cleanPercent", false, "0"),
        new InputItem("housing_naturalGas_therms_year", false, "472"),
        new InputItem("housing_heatingOil_gallons_year", false, "73"),
        new InputItem("housing_square_feet", false, "1850"),
        new InputItem("housing_water_sewage", false, "100"),
        new InputItem("food_meat_fish_eggs", true, "2.4"),
        new InputItem("food_grains", true, "4.1"),
        new InputItem("food_dairy", true, "2.2"),
        new InputItem("food_fruit_vegetables", true, "3.5"),
        new InputItem("food_snacks_drinks", true, "3.4"),
        new InputItem("shopping_goods", false, "1310"),
        new InputItem("shopping_services", false, "2413")
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
        if (Objects.requireNonNull(item).getFloat()) {
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
}
