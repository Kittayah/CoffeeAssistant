package org.fokkittah.coffeeassistant.panelsGUI.recipes;

import org.fokkittah.coffeeassistant.configuration.SettingsService;
import org.fokkittah.coffeeassistant.configuration.recipe.Recipe;
import org.fokkittah.coffeeassistant.configuration.recipe.Step;
import org.fokkittah.coffeeassistant.panelsGUI.welcomeScreen.CardLayoutManager;
import org.fokkittah.coffeeassistant.utils.RecipeStepTableRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.util.ArrayList;
import java.util.List;


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
    private JTable recipeStepsTable;
    private JButton addStepButton;
    private JButton moveUpStepButton;
    private JButton moveDownStepButton;
    private JPanel stepButtonControlPanel;
    private JScrollPane scrollTablePanel;
    private JLabel coffeAmountLabel;
    private JSpinner coffeeAmountSpinner;

    private SettingsService settingsService;

    public RecipesGui(CardLayoutManager manager, SettingsService settingsService) {
        this.settingsService = settingsService;

        initializeTable();
        initializeButtons(manager);
    }

    private void initializeTable() {
        String[] columnNames = {"Step Duration", "Water", "Step Info"};
        String[] tooltips = {"Duration of the step in seconds", "Amount of water in grams", "Information about step"};

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
//        DefaultTableModel modelOk = new DefaultTableModel(tutajDajWierszeChceVectora:D, columnNames);
        recipeStepsTable.setModel(model);
            //fixme
//        JTableHeader header = recipeStepsTable.getTableHeader();
////        DefaultTableColumnModel defaultTableColumnModel = new DefaultTableColumnModel();
////        defaultTableColumnModel.addColumn(new TableColumn(0, 10);
//        header.setColumnModel(new DefaultTableColumnModel());
//        header.setDefaultRenderer(new RecipeStepTableRenderer(recipeStepsTable, tooltips));

    }

    private void initializeButtons(CardLayoutManager manager) {
        newButton.addActionListener(e -> {clearRecipeFields();});
        goBackButton.addActionListener(e -> manager.switchPanel("main"));

        addStepButton.addActionListener(e -> addStep());
        deleteStepButton.addActionListener(e -> deleteStep());
        moveUpStepButton.addActionListener(e -> moveStepUp());
        moveDownStepButton.addActionListener(e -> moveStepDown());

        loadButton.addActionListener(e -> {
            LoadRecipeDialog loadRecipeDialog = new LoadRecipeDialog(settingsService, this);
            loadRecipeDialog.pack();
            loadRecipeDialog.setVisible(true);
        });

        saveButton.addActionListener(e -> {
            if (checkIfFieldsAreFilled()){
                saveRecipe();
            }
        });

        coffeeAmountSpinner.setModel(new SpinnerNumberModel(0, 0, 420, 1));

    }

    private void saveRecipe() {
        //todo
        Recipe recipe = new Recipe();
        recipe.setName(recipeInput.getText());
        recipe.setDescription(descriptionTextArea.getText());
        recipe.setCoffee(coffeInput.getText());
        recipe.setGrind(grindTextField.getText());
        recipe.setTotalWater(getAllWaterFromStepsInTable());
        recipe.setTotalCoffee((int) coffeeAmountSpinner.getValue());
        recipe.setSteps(fetchStepsFromTable());

        settingsService.getSettings().getRecipes().add(recipe);
        settingsService.saveSettings();

    }

    private int getAllWaterFromStepsInTable() {
        DefaultTableModel model = (DefaultTableModel) recipeStepsTable.getModel();
        int totalWater = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            totalWater += Integer.parseInt((String) model.getValueAt(i, 1));
        }
        return totalWater;
    }

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

    protected void loadRecipeStepsIntoTable(List<Step> recipeSteps){
        DefaultTableModel model = (DefaultTableModel) recipeStepsTable.getModel();
        model.setRowCount(0);
        for (Step step : recipeSteps) {
            model.addRow(new Object[]{String.valueOf(step.getDuration()), String.valueOf(step.getWater()), step.getStepInfo()});
        }
    }

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

    private void deleteStep() {
        DefaultTableModel model = (DefaultTableModel) recipeStepsTable.getModel();
        int rowCount = model.getRowCount();
        if (rowCount > 0) {
            model.removeRow(rowCount - 1);
        }
    }

    private void moveStepUp() {
        DefaultTableModel model = (DefaultTableModel) recipeStepsTable.getModel();
        int selectedRow = recipeStepsTable.getSelectedRow();
        if (selectedRow > 0) {
            model.moveRow(selectedRow, selectedRow, selectedRow - 1);
            recipeStepsTable.setRowSelectionInterval(selectedRow - 1, selectedRow - 1);
        }
    }

    private void moveStepDown() {
        DefaultTableModel model = (DefaultTableModel) recipeStepsTable.getModel();
        int selectedRow = recipeStepsTable.getSelectedRow();
        int rowCount = model.getRowCount();
        if (selectedRow < rowCount - 1) {
            model.moveRow(selectedRow, selectedRow, selectedRow + 1);
            recipeStepsTable.setRowSelectionInterval(selectedRow + 1, selectedRow + 1);
        }
    }

    private boolean isValidNumber(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidStepDuration(String input) {
        if (input.matches("\\d+:\\d{2}") || isValidNumber(input)) {
            return true;
        } else {
            return false;
        }
    }

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

    private boolean checkIfFieldsAreFilled() {
        // also check if the fields are not "type here..."
        if (recipeInput.getText().isEmpty() || coffeInput.getText().isEmpty() || grindTextField.getText().isEmpty() || descriptionTextArea.getText().isEmpty()
                || descriptionTextArea.getText().equals("type here...") || grindTextField.getText().equals("type here...") || coffeInput.getText().equals("type here...") || recipeInput.getText().equals("type here...")) {
                 JOptionPane.showMessageDialog(mainPanel, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
                 return false;
        } else {
            return true;
        }
    }

    private void clearRecipeFields() {
        recipeInput.setText("");
        coffeInput.setText("");
        grindTextField.setText("");
        descriptionTextArea.setText("");
        totalWaterSummaryLabel.setText("0g");
        totalCoffeAmountSummaryLabel.setText("0g");
        ((DefaultTableModel) recipeStepsTable.getModel()).setRowCount(0);
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

    public JSpinner getCoffeeAmountSpinner() {
        return coffeeAmountSpinner;
    }

    public JTable getRecipeStepsTable() {
        return recipeStepsTable;
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

    public void setCoffeeAmountSpinner(JSpinner coffeeAmountSpinner) {
        this.coffeeAmountSpinner = coffeeAmountSpinner;
    }

    public void setRecipeStepsTable(JTable recipeStepsTable) {
        this.recipeStepsTable = recipeStepsTable;
    }

}
