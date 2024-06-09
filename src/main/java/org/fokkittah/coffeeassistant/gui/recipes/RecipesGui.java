package org.fokkittah.coffeeassistant.gui.recipes;

import org.fokkittah.coffeeassistant.configuration.SettingsService;
import org.fokkittah.coffeeassistant.configuration.recipe.Recipe;
import org.fokkittah.coffeeassistant.configuration.recipe.Step;
import org.fokkittah.coffeeassistant.gui.layoutmanager.CardLayoutManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides the GUI for managing coffee recipes in the Coffee Assistant application.
 * It allows users to create, edit, save, load, and delete coffee recipes.
 */
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
    private JTextField coffeeInput;
    private JLabel coffeeLabel;
    private JTextArea descriptionTextArea;
    private JLabel grindLabel;
    private JLabel totalWaterLabel;
    private JLabel totalWaterSummaryLabel;
    private JLabel totalCoffeeAmountLabel;
    private JLabel totalCoffeeAmountSummaryLabel;
    private JTextField grindTextField;
    private JButton deleteStepButton;
    private JPanel inputPanel;
    private JLabel stepDurationLabel;
    private JLabel stepWaterLabel;
    private JTextField stepDurationInput;
    private JTextField stepWaterInput;
    private JTextField stepInfoInput;
    private JTable recipeStepsTable;
    private JButton addStepButton;
    private JButton moveUpStepButton;
    private JButton moveDownStepButton;
    private JPanel stepButtonControlPanel;
    private JScrollPane scrollTablePanel;
    private JLabel coffeeAmountLabel;
    private JSpinner coffeeAmountSpinner;

    private final SettingsService settingsService;

    /**
     * Constructs a RecipesGui object.
     *
     * @param manager The CardLayoutManager to manage panel switching.
     * @param settingsService The SettingsService to manage application settings.
     */
    public RecipesGui(CardLayoutManager manager, SettingsService settingsService) {
        this.settingsService = settingsService;

        initializeTable();
        initializeButtons(manager);
    }

    /**
     * Initializes the recipe steps table.
     */
    private void initializeTable() {
        String[] columnNames = {"Step Duration", "Water", "Step Info"};
        String[] tooltips = {"Duration of the step in seconds", "Amount of water in grams", "Information about step"};

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        recipeStepsTable.setModel(model);
    }

    /**
     * Initializes the buttons and their actions.
     *
     * @param manager The CardLayoutManager to manage panel switching.
     */
    private void initializeButtons(CardLayoutManager manager) {
        newButton.addActionListener(e -> clearRecipeFields());
        goBackButton.addActionListener(e -> manager.switchPanel(CardLayoutManager.PanelName.MAIN));

        addStepButton.addActionListener(e -> addStep());
        deleteStepButton.addActionListener(e -> deleteStep());
        moveUpStepButton.addActionListener(e -> moveStepUp());
        moveDownStepButton.addActionListener(e -> moveStepDown());

        loadButton.addActionListener(e -> loadRecipeDialog());

        saveButton.addActionListener(e -> {
            if (checkIfFieldsAreFilled()) {
                saveRecipe();
            }
        });

        coffeeAmountSpinner.setModel(new SpinnerNumberModel(0, 0, 420, 1));
    }

    /**
     * Opens the load recipe dialog.
     */
    private void loadRecipeDialog() {
        LoadRecipeDialog loadRecipeDialog = new LoadRecipeDialog(settingsService, this);
        loadRecipeDialog.pack();
        loadRecipeDialog.setVisible(true);
    }

    /**
     * Saves the current recipe to the settings.
     */
    private void saveRecipe() {
        Recipe recipe = new Recipe();
        recipe.setName(recipeInput.getText());
        recipe.setDescription(descriptionTextArea.getText());
        recipe.setCoffee(coffeeInput.getText());
        recipe.setGrind(grindTextField.getText());
        recipe.setTotalWater(getAllWaterFromStepsInTable());
        recipe.setTotalCoffee((int) coffeeAmountSpinner.getValue());
        recipe.setSteps(fetchStepsFromTable());

        settingsService.getSettings().getRecipes().add(recipe);
        settingsService.saveSettings();
    }

    /**
     * Calculates the total amount of water used in the recipe steps.
     *
     * @return The total amount of water in grams.
     */
    private int getAllWaterFromStepsInTable() {
        DefaultTableModel model = (DefaultTableModel) recipeStepsTable.getModel();
        int totalWater = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            totalWater += Integer.parseInt((String) model.getValueAt(i, 1));
        }
        return totalWater;
    }

    /**
     * Fetches the list of steps from the steps table.
     *
     * @return A list of steps.
     */
    private List<Step> fetchStepsFromTable() {
        DefaultTableModel model = (DefaultTableModel) recipeStepsTable.getModel();
        List<Step> steps = new ArrayList<>();
        for (int i = 0; i < model.getRowCount(); i++) {
            Step step = new Step();
            step.setDuration(Integer.parseInt((String) model.getValueAt(i, 0)));
            step.setWater(Integer.parseInt((String) model.getValueAt(i, 1)));
            step.setStepInfo((String) model.getValueAt(i, 2));
            steps.add(step);
        }
        return steps;
    }

    /**
     * Loads the steps of a recipe into the steps table.
     *
     * @param recipeSteps The list of steps to load.
     */
    protected void loadRecipeStepsIntoTable(List<Step> recipeSteps) {
        DefaultTableModel model = (DefaultTableModel) recipeStepsTable.getModel();
        model.setRowCount(0);
        for (Step step : recipeSteps) {
            model.addRow(new Object[]{String.valueOf(step.getDuration()), String.valueOf(step.getWater()), step.getStepInfo()});
        }
    }

    /**
     * Adds a new step to the recipe steps table.
     */
    private void addStep() {
        DefaultTableModel model = (DefaultTableModel) recipeStepsTable.getModel();
        String stepDuration = stepDurationInput.getText();
        String stepWater = stepWaterInput.getText();
        String stepInfo = stepInfoInput.getText();

        if (isValidStepDuration(stepDuration) && isValidNumber(stepWater)) {
            stepDuration = convertToSeconds(stepDuration);
            model.addRow(new Object[]{stepDuration, stepWater, stepInfo});
        } else {
            JOptionPane.showMessageDialog(mainPanel, "Invalid input for step duration or water amount", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Deletes the last step from the recipe steps table.
     */
    private void deleteStep() {
        DefaultTableModel model = (DefaultTableModel) recipeStepsTable.getModel();
        int rowCount = model.getRowCount();
        if (rowCount > 0) {
            model.removeRow(rowCount - 1);
        }
    }

    /**
     * Moves the selected step up in the recipe steps table.
     */
    private void moveStepUp() {
        DefaultTableModel model = (DefaultTableModel) recipeStepsTable.getModel();
        int selectedRow = recipeStepsTable.getSelectedRow();
        if (selectedRow > 0) {
            model.moveRow(selectedRow, selectedRow, selectedRow - 1);
            recipeStepsTable.setRowSelectionInterval(selectedRow - 1, selectedRow - 1);
        }
    }

    /**
     * Moves the selected step down in the recipe steps table.
     */
    private void moveStepDown() {
        DefaultTableModel model = (DefaultTableModel) recipeStepsTable.getModel();
        int selectedRow = recipeStepsTable.getSelectedRow();
        int rowCount = model.getRowCount();
        if (selectedRow < rowCount - 1) {
            model.moveRow(selectedRow, selectedRow, selectedRow + 1);
            recipeStepsTable.setRowSelectionInterval(selectedRow + 1, selectedRow + 1);
        }
    }

    /**
     * Validates if the input is a valid number.
     *
     * @param input The input string to validate.
     * @return True if the input is a valid number, false otherwise.
     */
    private boolean isValidNumber(String input) {
        try {
            Integer.parseInt(input);
            return Integer.valueOf(input) >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validates if the input is a valid step duration.
     *
     * @param input The input string to validate.
     * @return True if the input is a valid step duration, false otherwise.
     */
    private boolean isValidStepDuration(String input) {
        return input.matches("[1-9]\\d*:\\d{2}") || isValidNumber(input);
    }

    /**
     * Validates if the recipe input is valid.
     *
     * @param input The input string to validate.
     * @return True if the input is valid, false otherwise.
     */
    private boolean isValidRecipeInput(String input) {
        return input.length() <= 40;
    }

    /**
     * Validates if the grind input is valid.
     *
     * @param input The input string to validate.
     * @return True if the input is valid, false otherwise.
     */
    private boolean isValidGrindInput(String input) {
        return input.length() <= 15;
    }

    /**
     * Validates if the coffee input is valid.
     *
     * @param input The input string to validate.
     * @return True if the input is valid, false otherwise.
     */
    private boolean isValidCoffeeInput(String input) {
        return input.length() <= 25;
    }

    /**
     * Validates if the description input is valid.
     *
     * @param input The input string to validate.
     * @return True if the input is valid, false otherwise.
     */
    private boolean isValidDescriptionInput(String input) {
        return input.length() <= 400;
    }

    /**
     * Checks if the steps list is not empty.
     *
     * @return True if the steps list is not empty, false otherwise.
     */
    private boolean isStepListNotEmpty() {
        return recipeStepsTable.getRowCount() > 0;
    }

    /**
     * Checks if the recipe name is unique.
     *
     * @param recipeName The recipe name to check.
     * @return True if the recipe name is unique, false otherwise.
     */
    private boolean isUniqueCoffeeRecipe(String recipeName) {
        List<Recipe> recipes = settingsService.getSettings().getRecipes();
        for (Recipe recipe : recipes) {
            if (recipe.getName().equals(recipeName)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Converts a time string in minutes:seconds format to seconds.
     *
     * @param input The time string to convert.
     * @return The time in seconds.
     */
    private String convertToSeconds(String input) {
        if (input.matches("\\d+:\\d{2}")) {
            String[] parts = input.split(":");
            int minutes = Integer.parseInt(parts[0]);
            int seconds = Integer.parseInt(parts[1]);
            return String.valueOf(minutes * 60 + seconds);
        } else {
            return input;
        }
    }

    /**
     * Checks if all required fields are filled and valid.
     *
     * @return True if all fields are filled and valid, false otherwise.
     */
    private boolean checkIfFieldsAreFilled() {
        if (recipeInput.getText().isEmpty() || coffeeInput.getText().isEmpty() || grindTextField.getText().isEmpty() || descriptionTextArea.getText().isEmpty()
                || descriptionTextArea.getText().equals("type here...") || grindTextField.getText().equals("type here...") || coffeeInput.getText().equals("type here...") || recipeInput.getText().equals("type here...")) {
            JOptionPane.showMessageDialog(mainPanel, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (!isValidRecipeInput(recipeInput.getText())) {
            JOptionPane.showMessageDialog(mainPanel, "Recipe name is too long, cannot be more than 40 characters", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (!isValidCoffeeInput(coffeeInput.getText())) {
            JOptionPane.showMessageDialog(mainPanel, "Coffee name is too long, cannot be longer than 25 characters", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (!isValidGrindInput(grindTextField.getText())) {
            JOptionPane.showMessageDialog(mainPanel, "Grind name is too long, cannot be longer than 15 characters", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (!isValidDescriptionInput(descriptionTextArea.getText())) {
            JOptionPane.showMessageDialog(mainPanel, "Description is too long, cannot be longer than 400 characters", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (!isStepListNotEmpty()) {
            JOptionPane.showMessageDialog(mainPanel, "Please add at least one step to the recipe", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (!isUniqueCoffeeRecipe(recipeInput.getText())) {
            JOptionPane.showMessageDialog(mainPanel, "Recipe name must be unique", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            return true;
        }
    }

    /**
     * Clears all recipe fields in the GUI.
     */
    private void clearRecipeFields() {
        recipeInput.setText("");
        coffeeInput.setText("");
        grindTextField.setText("");
        descriptionTextArea.setText("");
        totalWaterSummaryLabel.setText("0g");
        totalCoffeeAmountSummaryLabel.setText("0g");
        ((DefaultTableModel) recipeStepsTable.getModel()).setRowCount(0);
    }

    /**
     * Returns the main panel of the Recipes GUI.
     *
     * @return The main panel.
     */
    public JPanel getPanel() {
        return mainPanel;
    }

    // Getter and setter methods for various components
    public JTextField getRecipeInput() {
        return recipeInput;
    }

    public JLabel getTotalWaterSummaryLabel() {
        return totalWaterSummaryLabel;
    }

    public JLabel getTotalCoffeeAmountSummaryLabel() {
        return totalCoffeeAmountSummaryLabel;
    }

    public JTextField getGrindTextField() {
        return grindTextField;
    }

    public JTextArea getDescriptionTextArea() {
        return descriptionTextArea;
    }

    public JTextField getCoffeeInput() {
        return coffeeInput;
    }

    public JSpinner getCoffeeAmountSpinner() {
        return coffeeAmountSpinner;
    }

    public JTable getRecipeStepsTable() {
        return recipeStepsTable;
    }

    public void setRecipeInput(JTextField recipeInput) {
        this.recipeInput = recipeInput;
    }

    public void setCoffeeInput(JTextField coffeeInput) {
        this.coffeeInput = coffeeInput;
    }

    public void setDescriptionTextArea(JTextArea descriptionTextArea) {
        this.descriptionTextArea = descriptionTextArea;
    }

    public void setTotalWaterSummaryLabel(JLabel totalWaterSummaryLabel) {
        this.totalWaterSummaryLabel = totalWaterSummaryLabel;
    }

    public void setTotalCoffeeAmountSummaryLabel(JLabel totalCoffeeAmountSummaryLabel) {
        this.totalCoffeeAmountSummaryLabel = totalCoffeeAmountSummaryLabel;
    }

    public void setGrindTextField(JTextField grindTextField) {
        this.grindTextField = grindTextField;
    }

    public void setCoffeeAmountSpinner(JSpinner coffeeAmountSpinner) {
        this.coffeeAmountSpinner = coffeeAmountSpinner;
    }

    public void setRecipeStepsTable(JTable recipeStepsTable) {
        this.recipeStepsTable = recipeStepsTable;
    }
}
