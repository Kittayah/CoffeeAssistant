package org.fokkittah.coffeeassistant.configuration.recipe;

import java.time.Duration;

public class Step {
    Integer duration;
    Integer water;
    String stepInfo;

    public Step(){
    }

    public Step(Integer duration, Integer water, String stepInfo) {
        this.duration = duration;
        this.water = water;
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
}