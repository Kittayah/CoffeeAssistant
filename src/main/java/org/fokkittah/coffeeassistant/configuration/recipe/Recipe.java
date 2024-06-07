package org.fokkittah.coffeeassistant.configuration.recipe;

import java.util.List;

public class Recipe {
    String name;
    String description;

    String coffee;
    String grind;

    Integer totalWater;
    Integer totalCoffee;

    List<Step> steps;

    public Recipe() {
    }

    public Recipe(String name, String description, String coffee, String grind, Integer totalWater, Integer totalCoffee, List<Step> steps) {
        this.name = name;
        this.description = description;
        this.coffee = coffee;
        this.grind = grind;
        this.totalWater = totalWater;
        this.totalCoffee = totalCoffee;
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

    public String getGrind() {
        return grind;
    }

    public Integer getTotalWater() {
        return totalWater;
    }

    public Integer getTotalCoffee() {
        return totalCoffee;
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

    public void setGrind(String grind) {
        this.grind = grind;
    }

    public void setTotalWater(Integer totalWater) {
        this.totalWater = totalWater;
    }

    public void setTotalCoffee(Integer totalCoffee) {
        this.totalCoffee = totalCoffee;
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
                ", grind='" + grind + '\'' +
                ", totalWater=" + totalWater +
                ", totalCoffee=" + totalCoffee +
                ", steps=" + steps +
                '}';
    }
}