package org.fokkittah.coffeeassistant.configuration.grinder;

public class GrindSettings{
    String brewingMethod;
    Integer min;
    Integer max;

    public GrindSettings() {
    }

    public GrindSettings(String brewingMethod, Integer min, Integer max) {
        this.brewingMethod = brewingMethod;
        this.min = min;
        this.max = max;
    }

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
