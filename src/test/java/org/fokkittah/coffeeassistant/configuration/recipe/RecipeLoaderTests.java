package org.fokkittah.coffeeassistant.configuration.recipe;

import org.junit.jupiter.api.Test;

import java.util.List;

public class RecipeLoaderTests {
    RecipeLoader recipeLoader = new RecipeLoader();

    @Test
    public void printAllRecipes(){

        List<Recipe> recipes = recipeLoader.loadAllRecipes("src/test/resources/recipes.xml");
        System.out.println(recipes);
    }
}
