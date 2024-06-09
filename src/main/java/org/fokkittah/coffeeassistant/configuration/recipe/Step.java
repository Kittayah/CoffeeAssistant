package org.fokkittah.coffeeassistant.configuration.recipe;

/**
 * This class represents a brewing step in a coffee recipe.
 * It includes properties such as duration, water amount, and step information.
 */
public class Step {
    Integer duration;
    Integer water;
    String stepInfo;

    /**
     * Default constructor for the Step class.
     */
    public Step(){
    }

    /**
     * Constructor for the Step class.
     * Initializes the duration, water amount, and step information with the provided values.
     * @param duration The duration of the step in seconds.
     * @param water The amount of water used in the step.
     * @param stepInfo The information about the step.
     */
    public Step(Integer duration, Integer water, String stepInfo) {
        this.duration = duration;
        this.water = water;
        this.stepInfo = stepInfo;
    }

    // Getters and setters for the Step class properties.

    public Integer getDuration() {
        return duration;
    }

    public Integer getWater() {
        return water;
    }

    public String getStepInfo() {
        return stepInfo;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public void setWater(Integer water) {
        this.water = water;
    }

    public void setStepInfo(String stepInfo) {
        this.stepInfo = stepInfo;
    }

    @Override
    public String toString() {
        return "Step{" +
                "duration=" + duration +
                ", waterAmount=" + water +
                ", stepInfo='" + stepInfo + '\'' +
                '}';
    }
}