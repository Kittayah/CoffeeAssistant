package org.fokkittah.coffeeassistant.state;

import org.fokkittah.coffeeassistant.configuration.grinder.Grinder;
import org.fokkittah.coffeeassistant.configuration.recipe.Recipe;

import java.io.Serializable;
import java.util.List;

public class CoffeeAssistantState implements Serializable {
    List<Grinder> grinderList;
    List<Recipe> recipeList;

    public CoffeeAssistantState(List<Grinder> grinderList, List<Recipe> recipeList) {
        this.grinderList = grinderList;
        this.recipeList = recipeList;
    }

    public CoffeeAssistantState() {
    }

    public void setGrinderList(List<Grinder> grinderList) {
        this.grinderList = grinderList;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    public List<Grinder> getGrinderList() {
        return grinderList;
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    @Override
    public String toString() {
        return "CoffeeAssistantState{" +
                "grinderList=" + grinderList +
                ", recipeList=" + recipeList +
                '}';
    }
}