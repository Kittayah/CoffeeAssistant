package org.fokkittah.coffeeassistant.configuration.recipe;

import java.time.Duration;
import java.util.List;

public class Recipe {
    String name;
    String description;

    String coffee;
    String brewer;
    String grind;

    Integer totalWater;
    Integer totalTime;

    List<Step> steps;

    public Recipe() {
    }

    public Recipe(String name, String description, String coffee, String brewer, String grind, Integer totalWater, Integer totalTime, List<Step> steps) {
        this.name = name;
        this.description = description;
        this.coffee = coffee;
        this.brewer = brewer;
        this.grind = grind;
        this.totalWater = totalWater;
        this.totalTime = totalTime;
        this.steps = steps;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCoffee() {
        return coffee;
    }

    public String getBrewer() {
        return brewer;
    }

    public String getGrind() {
        return grind;
    }

    public Integer getTotalWater() {
        return totalWater;
    }

    public Integer getTotalTime() {
        return totalTime;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCoffee(String coffee) {
        this.coffee = coffee;
    }

    public void setBrewer(String brewer) {
        this.brewer = brewer;
    }

    public void setGrind(String grind) {
        this.grind = grind;
    }

    public void setTotalWater(Integer totalWater) {
        this.totalWater = totalWater;
    }

    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", coffee='" + coffee + '\'' +
                ", brewer='" + brewer + '\'' +
                ", grind='" + grind + '\'' +
                ", totalWater=" + totalWater +
                ", totalTime=" + totalTime +
                ", steps=" + steps +
                '}';
    }
}