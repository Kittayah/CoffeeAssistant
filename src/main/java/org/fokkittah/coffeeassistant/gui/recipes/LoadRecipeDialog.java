package org.fokkittah.coffeeassistant.gui.recipes;

import org.fokkittah.coffeeassistant.configuration.SettingsService;
import org.fokkittah.coffeeassistant.configuration.recipe.Recipe;
import org.fokkittah.coffeeassistant.utils.ComboBoxRecipeRenderer;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;
import java.util.Vector;

/**
 * Dialog for loading a recipe in the Coffee Assistant application.
 * Allows users to select and load a recipe from the available recipes.
 */
public class LoadRecipeDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel selectRecipeLabel;
    private JComboBox<Recipe> recipeComboBox;

    private final SettingsService settingsService;

    /**
     * Constructs a LoadRecipeDialog.
     *
     * @param settingsService The SettingsService to retrieve application settings.
     * @param recipesGui The RecipesGui to update with the selected recipe.
     */
    public LoadRecipeDialog(SettingsService settingsService, RecipesGui recipesGui) {
        this.settingsService = settingsService;

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        // Add action listeners for buttons
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK(recipesGui);
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // Handle window closing event
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // Handle escape key press
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        // Fill the combo box with available recipes and set its renderer
        fillRecipeComboBox(settingsService.getSettings().getRecipes());
        recipeComboBox.setRenderer(new ComboBoxRecipeRenderer());

        // Center the dialog on the screen
        setLocationRelativeTo(null);
    }

    /**
     * Fills the recipe combo box with the provided list of recipes.
     *
     * @param recipes The list of recipes to populate the combo box.
     */
    private void fillRecipeComboBox(List<Recipe> recipes) {
        recipeComboBox.setModel(new DefaultComboBoxModel<>(new Vector<>(recipes)));
    }

    /**
     * Handles the OK button action.
     * Loads the selected recipe into the RecipesGui.
     *
     * @param recipesGui The RecipesGui to update with the selected recipe.
     */
    private void onOK(RecipesGui recipesGui) {
        Recipe selectedRecipe = (Recipe) recipeComboBox.getSelectedItem();
        if (selectedRecipe == null) {
            JOptionPane.showMessageDialog(this, "Please select a recipe to load", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            loadRecipe(selectedRecipe, recipesGui);
            dispose();
        }
    }

    /**
     * Loads the selected recipe into the provided RecipesGui.
     *
     * @param recipe The selected recipe to load.
     * @param recipesGui The RecipesGui to update with the recipe details.
     */
    public void loadRecipe(Recipe recipe, RecipesGui recipesGui) {
        recipesGui.getRecipeInput().setText(recipe.getName());
        recipesGui.getDescriptionTextArea().setText(recipe.getDescription());
        recipesGui.getCoffeeInput().setText(recipe.getCoffee());
        recipesGui.getGrindTextField().setText(recipe.getGrind());
        recipesGui.getTotalWaterSummaryLabel().setText(recipe.getTotalWater().toString());
        recipesGui.getTotalCoffeeAmountSummaryLabel().setText(recipe.getTotalCoffee().toString());
        recipesGui.getCoffeeAmountSpinner().setValue(recipe.getTotalCoffee());
        recipesGui.loadRecipeStepsIntoTable(recipe.getSteps());
    }

    /**
     * Handles the cancel button action.
     * Disposes of the dialog.
     */
    private void onCancel() {
        dispose();
    }
}
