package org.fokkittah.coffeeassistant.configuration.grinder;

/**
 * This class represents the grind settings for a coffee grinder.
 * It includes properties such as brewing method, minimum grind size, and maximum grind size.
 */
public class GrindSettings{
    String brewingMethod;
    Integer min;
    Integer max;

    /**
     * Default constructor for the GrindSettings class.
     */
    public GrindSettings() {
    }

    /**
     * Constructor for the GrindSettings class.
     * Initializes the brewing method, minimum grind size, and maximum grind size with the provided values.
     * @param brewingMethod The brewing method for which the grind settings apply.
     * @param min The minimum grind size for the brewing method.
     * @param max The maximum grind size for the brewing method.
     */
    public GrindSettings(String brewingMethod, Integer min, Integer max) {
        this.brewingMethod = brewingMethod;
        this.min = min;
        this.max = max;
    }

    // Getters and setters for the GrindSettings class properties.
    public String getBrewingMethod() {
        return brewingMethod;
    }

    public void setBrewingMethod(String brewingMethod) {
        this.brewingMethod = brewingMethod;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }
}
