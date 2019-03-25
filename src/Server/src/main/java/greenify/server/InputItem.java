package greenify.server;

public class InputItem {
    private String name;
    private Boolean isFloat;
    private String defaultValue;
    private Boolean isPresentByDefault = true;

    /**
     * Constructor for input items.
     * @param name of the input
     * @param isFloat whether it is float or not
     * @param defaultValue states the value
     */
    public InputItem(String name, boolean isFloat, String defaultValue) {
        this.name = name;
        this.isFloat = isFloat;
        this.defaultValue = defaultValue;
    }

    /**
     * Constructor for input items.
     * @param name of the input
     * @param isFloat whether it is float or not
     * @param defaultValue states the value
     * @param isPresentByDefault for different number of cars
     */
    public InputItem(String name, Boolean isFloat, String defaultValue,
                     Boolean isPresentByDefault) {
        this.name = name;
        this.isFloat = isFloat;
        this.defaultValue = defaultValue;
        this.isPresentByDefault = isPresentByDefault;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getFloat() {
        return isFloat;
    }

    public void setFloat(Boolean isFloat) {
        this.isFloat = isFloat;
    }
}
