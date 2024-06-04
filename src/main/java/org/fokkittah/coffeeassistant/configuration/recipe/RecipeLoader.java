package org.fokkittah.coffeeassistant.configuration.recipe;

import org.apache.commons.configuration2.HierarchicalConfiguration;
import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.tree.ImmutableNode;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class RecipeLoader {

    public List<Recipe> loadAllRecipes(String filePath) {
        Configurations configs = new Configurations();
        XMLConfiguration recipesXml;

        try {
            recipesXml = configs.xml(new File(filePath));
            List<HierarchicalConfiguration<ImmutableNode>> recipes = recipesXml.configurationsAt("recipe");

            List<Recipe> allRecipes = new ArrayList<>();

            for (HierarchicalConfiguration recipe : recipes) {

                List<Step> recipeSteps = new ArrayList<>();

                String name = recipe.getString("name");
                String description = recipe.getString("description");
                String coffee = recipe.getString("coffee");
                String brewer = recipe.getString("brewer");
                String grind = recipe.getString("grind");
                Integer totalWater = recipe.getInt("totalWater");
                Duration totalTime = recipe.getDuration("totalTime");

                List<HierarchicalConfiguration<ImmutableNode>> steps = recipe.configurationsAt("steps.step");
                for (HierarchicalConfiguration stepList : steps) {
                    Duration stepDuration = stepList.getDuration("duration");
                    Integer stepWater = stepList.getInt("water");
                    String stepInfo = stepList.getString("stepInfo");

                    Step step = new Step(stepDuration, stepWater, stepInfo);
                    recipeSteps.add(step);
                }

                Recipe preparedRecipe = new Recipe(name, description, coffee, brewer, grind, totalWater, totalTime, recipeSteps);
                allRecipes.add(preparedRecipe);
            }
            return allRecipes;

        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }
    }
}
