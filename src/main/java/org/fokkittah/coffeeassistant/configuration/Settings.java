package org.fokkittah.coffeeassistant.configuration;

import org.fokkittah.coffeeassistant.configuration.grinder.Grinder;
import org.fokkittah.coffeeassistant.configuration.recipe.Recipe;

import java.util.List;

public class Settings {

    private List<Grinder> grinders;
    private List<Recipe> recipes;

    public Settings() {
    }

    public Settings(List<Grinder> grinders, List<Recipe> recipes) {
        this.grinders = grinders;
        this.recipes = recipes;
    }

    public List<Grinder> getGrinders() {
        return grinders;
    }

    public void setGrinders(List<Grinder> grinders) {
        this.grinders = grinders;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "grindersList=" + grinders +
                ", recipesList=" + recipes +
                '}';
    }
}
