package org.fokkittah.coffeeassistant.configuration.recipe;

import java.util.List;

/**
 * This class represents a coffee brewing recipe.
 * It includes properties such as name, description, coffee type, grind size, total water, total coffee, and a list of brewing steps.
 */
public class Recipe {
    String name;
    String description;

    String coffee;
    String grind;

    Integer totalWater;
    Integer totalCoffee;

    List<Step> steps;

    /**
     * Default constructor for the Recipe class.
     */
    public Recipe() {
    }

    /**
     * Constructor for the Recipe class.
     * Initializes the name, description, coffee type, grind size, total water, total coffee, and list of brewing steps with the provided values.
     * @param name The name of the recipe.
     * @param description The description of the recipe.
     * @param coffee The type of coffee used in the recipe.
     * @param grind The grind size used in the recipe.
     * @param totalWater The total amount of water used in the recipe.
     * @param totalCoffee The total amount of coffee used in the recipe.
     * @param steps The list of brewing steps in the recipe.
     */
    public Recipe(String name, String description, String coffee, String grind, Integer totalWater, Integer totalCoffee, List<Step> steps) {
        this.name = name;
        this.description = description;
        this.coffee = coffee;
        this.grind = grind;
        this.totalWater = totalWater;
        this.totalCoffee = totalCoffee;
        this.steps = steps;
    }

    // Getters and setters for the Recipe class properties.
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