package org.fokkittah.coffeeassistant.gui.brewing;

import org.fokkittah.coffeeassistant.configuration.SettingsService;
import org.fokkittah.coffeeassistant.configuration.recipe.Recipe;
import org.fokkittah.coffeeassistant.configuration.recipe.Step;
import org.fokkittah.coffeeassistant.gui.layoutmanager.CardLayoutManager;
import org.fokkittah.coffeeassistant.utils.ComboBoxRecipeRenderer;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * This class represents the brewing GUI panel for the Coffee Assistant application.
 * It provides functionalities to select a recipe, start the brewing process, and monitor its progress.
 */
public class BrewingGui {

    private static final int PROGRESS_BAR_MAX_VALUE = 100000; // 100 seconds
    private static final int PROGRESS_BAR_REFRESH_DELAY = 1000; // 1 second
    private static final int DEFAULT_PROGRESS_BAR_STEP = 1000; // 1 second step

    private JPanel mainPanel;
    private JPanel leftSideControlPanel;
    private JLabel recipeLabel;
    private JPanel recipePanel;
    private JComboBox<Recipe> recipeComboBox;
    private JButton startButton;
    private JButton goBackButton;

    private JPanel recipeInfoPanel;
    private JPanel progressInfoPanel;
    private JLabel elapsedTimeLabel;
    private JLabel remainingTimeLabel;
    private JLabel elapsedLabel;
    private JLabel remainingLabel;
    private JPanel brewingInfoPanel;
    private JLabel currentStepLabel;
    private JLabel incomingStepsLabel;
    private JLabel recipeInfoLabel;

    private JLabel coffeeAmountLabel;
    private JLabel coffeeAmountValueLabel;
    private JLabel waterAmountLabel;
    private JLabel waterAmountValueLabel;
    private JLabel grinderSettingLabel;
    private JLabel grinderSettingValueLabel;
    private JLabel totalTimeLabel;
    private JLabel totalTimeValueLabel;
    private JTable incomingStepsTable;
    private JScrollPane incomingStepsScrollPane;
    private JLabel timeLeftLabel;
    private JLabel timeLeftValue;
    private JLabel waterToPourLabel;
    private JLabel waterToPourValue;
    private JScrollPane currentStepScrollPanel;
    private JTextArea currentStepTextArea;
    private JLabel coffeeKindLabel;
    private JLabel coffeeKindValueLabel;

    private JProgressBar brewingProgressBar;
    private int progressBarStep = DEFAULT_PROGRESS_BAR_STEP;
    private Iterator<Step> stepIterator;
    private Timer timer;

    private SettingsService settingsService;

    /**
     * Constructs a BrewingGui object.
     *
     * @param manager        The CardLayoutManager to manage panel switching.
     * @param settingsService The SettingsService to manage application settings.
     */
    public BrewingGui(CardLayoutManager manager, SettingsService settingsService) {
        this.settingsService = settingsService;
        initializeComponents(manager);
    }

    /**
     * Initializes components and sets up the GUI.
     *
     * @param manager The CardLayoutManager to manage panel switching.
     */
    private void initializeComponents(CardLayoutManager manager) {
        initializeButtons(manager);
        initializeRecipeStepsTable();
        initializeRecipeComboBox();
        initializeProgressBar();
    }

    /**
     * Initializes the recipe steps table.
     */
    private void initializeRecipeStepsTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Duration");
        model.addColumn("Water");
        model.addColumn("Step");
        incomingStepsTable.setModel(model);
    }

    /**
     * Initializes the progress bar.
     */
    private void initializeProgressBar() {
        brewingProgressBar.setMaximum(PROGRESS_BAR_MAX_VALUE);
    }

    /**
     * Initializes the buttons and their actions.
     *
     * @param manager The CardLayoutManager to manage panel switching.
     */
    private void initializeButtons(CardLayoutManager manager) {
        startButton.addActionListener(e -> startBrewing());
        goBackButton.addActionListener(e -> manager.switchPanel(CardLayoutManager.PanelName.MAIN));
    }

    /**
     * Clears the recipe steps table.
     */
    private void clearRecipeStepsTable() {
        DefaultTableModel model = (DefaultTableModel) incomingStepsTable.getModel();
        model.setRowCount(0);
    }

    /**
     * Starts the brewing process for the selected recipe.
     */
    private void startBrewing() {
        Recipe selectedRecipe = (Recipe) recipeComboBox.getSelectedItem();
        if (selectedRecipe != null) {
            prepareBrewing(selectedRecipe);
        } else {
            showErrorMessage("Please select a recipe to start brewing");
        }
    }

    /**
     * Prepares the brewing process.
     *
     * @param selectedRecipe The selected recipe.
     */
    private void prepareBrewing(Recipe selectedRecipe) {
        setRecipeComboBoxEnabled(false);
        setStartButtonEnabled(false);
        updateBrewingInfoLabels(selectedRecipe);
        stepIterator = selectedRecipe.getSteps().iterator();
        startBrewingStep();
    }

    /**
     * Updates the brewing info labels.
     *
     * @param selectedRecipe The selected recipe.
     */
    private void updateBrewingInfoLabels(Recipe selectedRecipe) {
        remainingTimeLabel.setText(convertTotalTimeFromSecondsToMinutes(settingsService.summarizeTotalTimeInRecipeInSeconds(selectedRecipe)));
        elapsedTimeLabel.setText("0:00");
    }

    /**
     * Starts the next step in the brewing process.
     */
    private void startBrewingStep() {
        if (stepIterator.hasNext()) {
            Step currentStep = stepIterator.next();
            showBrewingInfoDialog(currentStep);
        } else {
            showBrewingCompleteDialog();
        }
    }

    /**
     * Shows a dialog with information about the current brewing step.
     *
     * @param currentStep The current step in the brewing process.
     */
    private void showBrewingInfoDialog(Step currentStep) {
        showInfoMessage(
                String.format("Next Step:\nWhat to do: %s\nWater to pour: %d g\nDuration: %d seconds",
                        currentStep.getStepInfo(),
                        currentStep.getWater(),
                        currentStep.getDuration()));
        updateCurrentStepInfo(currentStep);
        startStepTimer(currentStep.getDuration());
    }

    /**
     * Updates the current step info.
     *
     * @param currentStep The current step in the brewing process.
     */
    private void updateCurrentStepInfo(Step currentStep) {
        currentStepTextArea.setText(currentStep.getStepInfo());
        timeLeftValue.setText(convertTotalTimeFromSecondsToMinutes(currentStep.getDuration()));
        waterToPourValue.setText(String.valueOf(currentStep.getWater()));
    }

    /**
     * Shows a dialog indicating that the brewing process is complete.
     */
    private void showBrewingCompleteDialog() {
        setRecipeComboBoxEnabled(true);
        setStartButtonEnabled(true);
        showInfoMessage("Brewing complete!");
    }

    /**
     * Starts a timer for the current brewing step.
     *
     * @param stepDuration The duration of the current step in seconds.
     */
    private void startStepTimer(int stepDuration) {
        progressBarStep = PROGRESS_BAR_MAX_VALUE / stepDuration;
        brewingProgressBar.setValue(0);
        timer = new Timer(PROGRESS_BAR_REFRESH_DELAY, progressBarUpdater);
        timer.start();
    }

    private final ActionListener progressBarUpdater = evt -> {
        if (brewingProgressBar.getValue() < PROGRESS_BAR_MAX_VALUE - PROGRESS_BAR_REFRESH_DELAY) {
            brewingProgressBar.setValue(brewingProgressBar.getValue() + progressBarStep);
            updateBrewingTimeLabels();
        } else {
            brewingProgressBar.setValue(PROGRESS_BAR_MAX_VALUE);
            timer.stop();
            startBrewingStep();
        }
    };

    /**
     * Updates the brewing time labels.
     */
    private void updateBrewingTimeLabels() {
        updateTimeLeftLabel();
        updateTimeRemainingLabel();
        updateTimeElapsedLabel();
    }

    /**
     * Sets the enabled state of the recipe combo box.
     *
     * @param enabled The new enabled state.
     */
    private void setRecipeComboBoxEnabled(boolean enabled) {
        recipeComboBox.setEnabled(enabled);
    }

    /**
     * Sets the enabled state of the start button.
     *
     * @param enabled The new enabled state.
     */
    private void setStartButtonEnabled(boolean enabled) {
        startButton.setEnabled(enabled);
    }

    /**
     * Initializes the recipe combo box with available recipes.
     */
    private void initializeRecipeComboBox() {
        fillRecipeComboBox(settingsService.getSettings().getRecipes());
        recipeComboBox.setRenderer(new ComboBoxRecipeRenderer());
        recipeComboBox.addActionListener

                (e -> {
                    clearRecipeStepsTable();
                    fillBrewingInfoPanelUponSelectingRecipe();
                });
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
     * Fills the brewing info panel with details of the selected recipe.
     */
    private void fillBrewingInfoPanelUponSelectingRecipe() {
        Recipe selectedRecipe = (Recipe) recipeComboBox.getSelectedItem();
        if (selectedRecipe != null) {
            updateBrewingInfoPanel(selectedRecipe);
            loadRecipeStepsIntoTable(selectedRecipe.getSteps());
        }
    }

    /**
     * Updates the brewing info panel with details of the selected recipe.
     *
     * @param selectedRecipe The selected recipe.
     */
    private void updateBrewingInfoPanel(Recipe selectedRecipe) {
        recipeInfoLabel.setText(selectedRecipe.getName());
        coffeeKindValueLabel.setText(selectedRecipe.getCoffee());
        coffeeAmountValueLabel.setText(selectedRecipe.getTotalCoffee().toString());
        waterAmountValueLabel.setText(selectedRecipe.getTotalWater().toString());
        grinderSettingValueLabel.setText(selectedRecipe.getGrind());
        totalTimeValueLabel.setText(convertTotalTimeFromSecondsToMinutes(settingsService.summarizeTotalTimeInRecipeInSeconds(selectedRecipe)));
    }

    /**
     * Loads the steps of the provided recipe into the steps table.
     *
     * @param recipeSteps The list of steps to load.
     */
    protected void loadRecipeStepsIntoTable(List<Step> recipeSteps) {
        DefaultTableModel model = (DefaultTableModel) incomingStepsTable.getModel();
        for (Step step : recipeSteps) {
            model.addRow(new Object[]{String.valueOf(step.getDuration()), String.valueOf(step.getWater()), step.getStepInfo()});
        }
    }

    /**
     * Converts total time from seconds to a minutes:seconds format.
     *
     * @param totalTimeInSeconds The total time in seconds.
     * @return The formatted time string.
     */
    private String convertTotalTimeFromSecondsToMinutes(int totalTimeInSeconds) {
        int minutes = totalTimeInSeconds / 60;
        int seconds = totalTimeInSeconds % 60;
        return String.format("%d:%02d", minutes, seconds);
    }

    /**
     * Converts total time from a minutes:seconds format to seconds.
     *
     * @param totalTimeInMinutes The total time in minutes:seconds format.
     * @return The total time in seconds.
     */
    private int convertTotalTimeFromReadableToSeconds(String totalTimeInMinutes) {
        String[] time = totalTimeInMinutes.split(":");
        return Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
    }

    /**
     * Updates the time left label.
     */
    private void updateTimeLeftLabel() {
        int currentTime = convertTotalTimeFromReadableToSeconds(timeLeftValue.getText());
        timeLeftValue.setText(convertTotalTimeFromSecondsToMinutes(currentTime - 1));
    }

    /**
     * Updates the remaining time label.
     */
    private void updateTimeRemainingLabel() {
        int currentTime = convertTotalTimeFromReadableToSeconds(remainingTimeLabel.getText());
        remainingTimeLabel.setText(convertTotalTimeFromSecondsToMinutes(currentTime - 1));
    }

    /**
     * Updates the elapsed time label.
     */
    private void updateTimeElapsedLabel() {
        int currentTime = convertTotalTimeFromReadableToSeconds(elapsedTimeLabel.getText());
        elapsedTimeLabel.setText(convertTotalTimeFromSecondsToMinutes(currentTime + 1));
    }

    /**
     * Shows an information message dialog.
     *
     * @param message The message to display.
     */
    private void showInfoMessage(String message) {
        JOptionPane.showMessageDialog(mainPanel, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Shows an error message dialog.
     *
     * @param message The message to display.
     */
    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(mainPanel, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Returns the main panel of the brewing GUI.
     *
     * @return The main panel.
     */
    public JPanel getPanel() {
        return mainPanel;
    }
}