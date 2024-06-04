package org.fokkittah.coffeeassistant.configuration.recipe;

import java.time.Duration;

public class Step {
    Duration duration;
    Integer waterAmount;
    String stepInfo;

    public Step(){
    }

    public Step(Duration duration, Integer waterAmount, String stepInfo) {
        this.duration = duration;
        this.waterAmount = waterAmount;
        this.stepInfo = stepInfo;
    }

    public Step(String duration, String waterAmount, String stepInfo) {
        this.duration = Duration.parse(duration);
        this.waterAmount = Integer.parseInt(waterAmount);
        this.stepInfo = stepInfo;
    }

    @Override
    public String toString() {
        return "Step{" +
                "duration=" + duration +
                ", waterAmount=" + waterAmount +
                ", stepInfo='" + stepInfo + '\'' +
                '}';
    }

    public Duration getDuration() {
        return duration;
    }

    public Integer getWaterAmount() {
        return waterAmount;
    }

    public String getStepInfo() {
        return stepInfo;
    }
}