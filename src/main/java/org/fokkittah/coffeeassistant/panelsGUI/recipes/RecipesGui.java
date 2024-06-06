package org.fokkittah.coffeeassistant.panelsGUI.recipes;

import org.fokkittah.coffeeassistant.configuration.SettingsService;
import org.fokkittah.coffeeassistant.panelsGUI.welcomeScreen.CardLayoutManager;

import javax.swing.*;


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
    private JTextField grindTextField;
    private JButton deleteStepButton;
    private JPanel inputPanel;
    private JLabel stepDurationLabel;
    private JLabel stepWaterLabel;
    private JTextField stepDurationInput;
    private JTextField stepWaterInput;
    private JTextField stepInfoInput;
    private JTable table1;
    private JButton addStepButton;
    private JButton moveUpStepButton;
    private JButton moveDownStepButton;

    private SettingsService settingsService;

    public RecipesGui(CardLayoutManager manager, SettingsService settingsService) {
        this.settingsService = settingsService;
        goBackButton.addActionListener(e -> manager.switchPanel("main"));

        loadButton.addActionListener(e -> {
            LoadRecipeDialog loadRecipeDialog = new LoadRecipeDialog(settingsService, this);
            loadRecipeDialog.pack();
            loadRecipeDialog.setVisible(true);
        });

        newButton.addActionListener(e -> {
            clearRecipeFields();
        });

        saveButton.addActionListener(e -> {
            //todo
            checkIfFieldsAreFilled();
        });


    }

    private void checkIfFieldsAreFilled() {
        // also check if the fields are not "type here..."
        if (recipeInput.getText().isEmpty() || coffeInput.getText().isEmpty() || grindTextField.getText().isEmpty() || descriptionTextArea.getText().isEmpty()
                || descriptionTextArea.getText().equals("type here...") || grindTextField.getText().equals("type here...") || coffeInput.getText().equals("type here...") || recipeInput.getText().equals("type here...")) {
                 JOptionPane.showMessageDialog(mainPanel, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            //todo
//            RecipeStepsDialog recipeStepsDialog = new RecipeStepsDialog(settingsService, this);
//            recipeStepsDialog.pack();
//            recipeStepsDialog.setVisible(true);
        }
    }

    private void clearRecipeFields() {
        recipeInput.setText("");
        coffeInput.setText("");
        grindTextField.setText("");
        descriptionTextArea.setText("");
        totalWaterSummaryLabel.setText("0g");
        totalCoffeAmountSummaryLabel.setText("0g");
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
