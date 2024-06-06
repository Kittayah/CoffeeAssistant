package org.fokkittah.coffeeassistant.panelsGUI.recipes;

import org.fokkittah.coffeeassistant.configuration.SettingsService;
import org.fokkittah.coffeeassistant.configuration.recipe.Recipe;
import org.fokkittah.coffeeassistant.utils.comboBoxRecipeRenderer;

import javax.swing.*;
import java.awt.event.*;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class LoadRecipeDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel selectRecipeLabel;
    private JComboBox recipeComboBox;

    SettingsService settingsService;


    public LoadRecipeDialog(SettingsService settingsService, RecipesGui recipesGui) {
        this.settingsService = settingsService;

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

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

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        fillRecipeComboBox(settingsService.getSettings().getRecipes());
        recipeComboBox.setRenderer(new comboBoxRecipeRenderer());



        setLocationRelativeTo(null);
    }

    private void fillRecipeComboBox(List<Recipe> recipes) {
        recipeComboBox.setModel(new DefaultComboBoxModel<>(new Vector<>(recipes)));
    }

    private void onOK(RecipesGui recipesGui) {
        // add your code here
        Recipe selectedRecipe = (Recipe) recipeComboBox.getSelectedItem();
        if (selectedRecipe == null) {
            JOptionPane.showMessageDialog(this, "Please select a recipe to load", "Error", JOptionPane.ERROR_MESSAGE);
        }

        recipesGui.getRecipeInput().setText(selectedRecipe.getName()); //
        recipesGui.getDescriptionTextArea().setText(selectedRecipe.getDescription());
        recipesGui.getCoffeInput().setText(selectedRecipe.getCoffee());
        recipesGui.getGrindTextField().setText(selectedRecipe.getGrind());

        recipesGui.getTotalWaterSummaryLabel().setText(selectedRecipe.getTotalWater().toString());
        recipesGui.getTotalCoffeAmountSummaryLabel().setText(selectedRecipe.getTotalTime().toString());




        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}
