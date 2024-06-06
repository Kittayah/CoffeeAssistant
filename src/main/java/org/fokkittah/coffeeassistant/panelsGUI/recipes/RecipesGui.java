package org.fokkittah.coffeeassistant.panelsGUI.recipes;

import org.fokkittah.coffeeassistant.configuration.SettingsService;
import org.fokkittah.coffeeassistant.panelsGUI.settings.AddGrinderDialog;
import org.fokkittah.coffeeassistant.panelsGUI.welcomeScreen.CardLayoutManager;
import org.fokkittah.coffeeassistant.utils.comboBoxGrinderRenderer;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class RecipesGui {
    private JPanel mainPanel;
    private JButton goBackButton;
    private JPanel controlPanel;
    private JButton saveButton;
    private JButton loadButton;
    private JButton newButton;
    private JPanel recipeEditPanel;
    private JLabel recipeName;
    private JTextField recipeInput;
    private JTextField coffeInput;
    private JLabel coffeeLabel;
    private JTextArea descriptionTextArea;
    private JLabel grindLabel;
    private JLabel totalWaterLabel;
    private JLabel totalWaterSummaryLabel;
    private JLabel totalCoffeeAmountLabel;
    private JLabel totalCoffeAmountSummaryLabel;
    private JButton editStepsButton;
    private JTextField grindTextField;

    private SettingsService settingsService;

    public RecipesGui(CardLayoutManager manager, SettingsService settingsService) {
        this.settingsService = settingsService;
        goBackButton.addActionListener(e -> manager.switchPanel("main"));

//        fillRecipeComboBox(settingsService.getSettings().getRecipes());
//        recipe
//
//
//        loadButton.addActionListener(e -> {
//            AddGrinderDialog addGrinderDialog = new AddGrinderDialog(settingsService);
//            addGrinderDialog.pack();
//            addGrinderDialog.setVisible(true);
//            addGrinderDialog.addWindowListener(new WindowAdapter() {
//                @Override
//                public void windowClosed(WindowEvent event){
//                    fillGrinderComboBox(settingsService.getSettings().getGrinders());
//                    grinderComboBox.setRenderer(new comboBoxGrinderRenderer());
//                }
//            });
//        });

        loadButton.addActionListener(e -> {
            LoadRecipeDialog loadRecipeDialog = new LoadRecipeDialog(settingsService, this);
            loadRecipeDialog.pack();
            loadRecipeDialog.setVisible(true);
        });

    }

    public JPanel getPanel() {
        return mainPanel;
    }

    public JTextField getRecipeInput() {
        return recipeInput;
    }

    public JLabel getTotalWaterSummaryLabel() {
        return totalWaterSummaryLabel;
    }

    public JLabel getTotalCoffeAmountSummaryLabel() {
        return totalCoffeAmountSummaryLabel;
    }

    public JTextField getGrindTextField() {
        return grindTextField;
    }

    public JTextArea getDescriptionTextArea() {
        return descriptionTextArea;
    }

    public JTextField getCoffeInput() {
        return coffeInput;
    }

    public void setRecipeInput(JTextField recipeInput) {
        this.recipeInput = recipeInput;
    }

    public void setCoffeInput(JTextField coffeInput) {
        this.coffeInput = coffeInput;
    }

    public void setDescriptionTextArea(JTextArea descriptionTextArea) {
        this.descriptionTextArea = descriptionTextArea;
    }

    public void setTotalWaterSummaryLabel(JLabel totalWaterSummaryLabel) {
        this.totalWaterSummaryLabel = totalWaterSummaryLabel;
    }

    public void setTotalCoffeAmountSummaryLabel(JLabel totalCoffeAmountSummaryLabel) {
        this.totalCoffeAmountSummaryLabel = totalCoffeAmountSummaryLabel;
    }

    public void setGrindTextField(JTextField grindTextField) {
        this.grindTextField = grindTextField;
    }

}
