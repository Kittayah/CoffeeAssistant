package org.fokkittah.coffeeassistant.configuration;

import org.fokkittah.coffeeassistant.configuration.grinder.Grinder;
import org.fokkittah.coffeeassistant.configuration.recipe.Recipe;

import java.util.List;

/**
 * This class represents the settings for the CoffeeAssistant application.
 * It includes a list of Grinder objects and a list of Recipe objects.
 */
public class Settings {

    private List<Grinder> grinders;
    private List<Recipe> recipes;

    /**
     * Default constructor for the Settings class.
     */
    public Settings() {
    }

    /**
     * Constructor for the Settings class.
     * Initializes the grinders and recipes with the provided lists.
     * @param grinders The list of Grinder objects.
     * @param recipes The list of Recipe objects.
     */
    public Settings(List<Grinder> grinders, List<Recipe> recipes) {
        this.grinders = grinders;
        this.recipes = recipes;
    }

    /**
     * Returns the list of Grinder objects.
     * @return The list of Grinder objects.
     */
    public List<Grinder> getGrinders() {
        return grinders;
    }

    /**
     * Sets the list of Grinder objects.
     * @param grinders The list of Grinder objects.
     */
    public void setGrinders(List<Grinder> grinders) {
        this.grinders = grinders;
    }

    /**
     * Returns the list of Recipe objects.
     * @return The list of Recipe objects.
     */
    public List<Recipe> getRecipes() {
        return recipes;
    }

    /**
     * Sets the list of Recipe objects.
     * @param recipes The list of Recipe objects.
     */
    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    /**
     * @return A string representation of the Settings object.
     */
    @Override
    public String toString() {
        return "Configuration{" +
                "grindersList=" + grinders +
                ", recipesList=" + recipes +
                '}';
    }
}