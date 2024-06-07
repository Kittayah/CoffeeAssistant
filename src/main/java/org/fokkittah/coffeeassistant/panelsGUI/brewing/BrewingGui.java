package org.fokkittah.coffeeassistant.panelsGUI.brewing;

import org.fokkittah.coffeeassistant.configuration.SettingsService;
import org.fokkittah.coffeeassistant.configuration.recipe.Recipe;
import org.fokkittah.coffeeassistant.configuration.recipe.Step;
import org.fokkittah.coffeeassistant.panelsGUI.welcomeScreen.CardLayoutManager;
import org.fokkittah.coffeeassistant.utils.ComboBoxRecipeRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;


public class BrewingGui {
    private JPanel mainPanel;
    private JPanel leftSideControlPanel;
    private JLabel recipeLabel;
    private JPanel recipePanel;
    private JComboBox recipeComboBox;
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

    // progress bar
    private JProgressBar brewingProgressBar;
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
    private Timer timer;
    public static final int PROGRESS_BAR_MAX_VALUE = 100000;
    public static final int PROGRESS_BAR_REFRESH_DELAY = 1000;
    public static int progressBarStep = 1000;
    Iterator<Step> stepIterator;

    SettingsService settingsService;


    public BrewingGui(CardLayoutManager manager, SettingsService settingsService) {
        this.settingsService = settingsService;
        initializeButtons(manager);
        initializeRecipeStepsTable();
        initializeRecipeComboBox();
        initializeProgressBar();

    }

    private void initializeRecipeStepsTable(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Duration");
        model.addColumn("Water");
        model.addColumn("Step");
        incomingStepsTable.setModel(model);
    }

    private void initializeProgressBar(){
        brewingProgressBar.setMaximum(PROGRESS_BAR_MAX_VALUE);
    }

    private void initializeButtons(CardLayoutManager manager){
        startButton.addActionListener(e -> {startBrewing();});
        goBackButton.addActionListener(e -> manager.switchPanel("main"));
    }

    private void clearRecipeStepsTable(){
        DefaultTableModel model = (DefaultTableModel) incomingStepsTable.getModel();
        model.setRowCount(0);
    }

    private void clearRecipeInfoPanel(){
        recipeInfoLabel.setText("");
        coffeeAmountValueLabel.setText("");
        waterAmountValueLabel.setText("");
        grinderSettingValueLabel.setText("");
        totalTimeValueLabel.setText("");
        DefaultTableModel model = (DefaultTableModel) incomingStepsTable.getModel();
        model.setRowCount(0);
    }

    private void startBrewing(){
        List<Step> stepList = new ArrayList<>();
        Recipe selectedRecipe = (Recipe) recipeComboBox.getSelectedItem();
        if (selectedRecipe != null){
            setRecipeComboBoxEnabled(false);
            setStartButtonEnabled(false);

            remainingTimeLabel.setText(convertTotalTimeFromSecondsToMinutes(settingsService.summarizeTotalTimeInRecipeInSeconds(selectedRecipe)));
            elapsedTimeLabel.setText("0:00");

            stepList = selectedRecipe.getSteps();
            stepIterator = stepList.iterator();
            startBrewingStep();
        } else {
            JOptionPane.showMessageDialog(mainPanel, "Please select a recipe to start brewing", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void startBrewingStep() {
        if (stepIterator.hasNext()) {
            Step currentStep = stepIterator.next();
            showBrewingInfoDialog(currentStep);
        } else {
            showBrewingCompleteDialog();
        }
    }

    private void showBrewingInfoDialog(Step currentStep){
        JOptionPane.showMessageDialog(mainPanel,
                "Next Step:\n" +
                        "What to do: " + currentStep.getStepInfo() + "\n" +
                        "Water to pour: " + currentStep.getWater() + " g\n" +
                        "Duration: " + currentStep.getDuration() + " seconds",
                "Info about next step",
                JOptionPane.INFORMATION_MESSAGE);
        currentStepTextArea.setText(currentStep.getStepInfo());
        timeLeftValue.setText(convertTotalTimeFromSecondsToMinutes(currentStep.getDuration()));
        waterToPourValue.setText(String.valueOf(currentStep.getWater()));

        startStepTimer(currentStep.getDuration());
    }

    private void showBrewingCompleteDialog(){
        setRecipeComboBoxEnabled(true);
        setStartButtonEnabled(true);
        JOptionPane.showMessageDialog(mainPanel,
                "Brewing complete!",
                "Information",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void startStepTimer(int stepDuration){
        progressBarStep = PROGRESS_BAR_MAX_VALUE / stepDuration;
        brewingProgressBar.setValue(0);
        timer = new Timer(PROGRESS_BAR_REFRESH_DELAY, progressBarUpdater);
        timer.start();
    }

    private final ActionListener progressBarUpdater = evt -> {
        if (brewingProgressBar.getValue() < PROGRESS_BAR_MAX_VALUE-1000) {
            brewingProgressBar.setValue(brewingProgressBar.getValue() + progressBarStep);
            updateTimeLeftLabel();
            updateTimeRemainingLabel();
            updateTimeElapsedLabel();
        } else {
            brewingProgressBar.setValue(PROGRESS_BAR_MAX_VALUE);
            timer.stop();
            startBrewingStep();
        }
    };

    private void setRecipeComboBoxEnabled(boolean b) {
        recipeComboBox.setEnabled(b);
    }

    private void setStartButtonEnabled(boolean b) {
        startButton.setEnabled(b);
    }

    private void initializeRecipeComboBox(){
        fillRecipeComboBox(settingsService.getSettings().getRecipes());
        recipeComboBox.setRenderer(new ComboBoxRecipeRenderer());
        recipeComboBox.addActionListener(e -> {
            clearRecipeStepsTable();
            fillBrewingInfoPanelUponSelectingRecipe();
        });
    }

    private void fillRecipeComboBox(List<Recipe> recipes){
        recipeComboBox.setModel(new DefaultComboBoxModel<>(new Vector<>(recipes)));
    }

    private void fillBrewingInfoPanelUponSelectingRecipe(){
        Recipe selectedRecipe = (Recipe) recipeComboBox.getSelectedItem();
        if(selectedRecipe != null){
            recipeInfoLabel.setText(selectedRecipe.getName());
            coffeeAmountValueLabel.setText(selectedRecipe.getTotalCoffee().toString());
            waterAmountValueLabel.setText(selectedRecipe.getTotalWater().toString());
            grinderSettingValueLabel.setText(selectedRecipe.getGrind());
            totalTimeValueLabel.setText(convertTotalTimeFromSecondsToMinutes((settingsService.summarizeTotalTimeInRecipeInSeconds(selectedRecipe))));
            loadRecipeStepsIntoTable(selectedRecipe.getSteps());
        }
    }

    protected void loadRecipeStepsIntoTable(List<Step> recipeSteps){
        DefaultTableModel model = (DefaultTableModel) incomingStepsTable.getModel();
        for (Step step : recipeSteps) {
            model.addRow(new Object[]{String.valueOf(step.getDuration()), String.valueOf(step.getWater()), step.getStepInfo()});
        }
    }

    //progress bar logic



//    void startProgressBar(){
//        List<Step> stepList = new ArrayList<>();
//        //example, to be replaced with steps from recipe
////        stepList.add(new Step(1, 60, "Bloom"));
////        stepList.add(new Step(5, 240, "Brew on closed valve"));
////        stepList.add(new Step(20, 240, "Brew on closed valve"));
//        stepIterator = stepList.iterator();
//        updateProgressBar();
//    }

//    void updateProgressBar(){
//        if(stepIterator.hasNext()){
//            Step currentStep = stepIterator.next();
//            int stepDuration = currentStep.getDuration();
//            progressBarStep = Math.toIntExact(PROGRESS_BAR_MAX_VALUE / PROGRESS_BAR_REFRESH_DELAY / stepDuration);
//            timer = new Timer(PROGRESS_BAR_REFRESH_DELAY, progressBarUpdater);
//            timer.start();
//        }
//    }

    private String convertTotalTimeFromSecondsToMinutes(int totalTimeInSeconds){
        int minutes = totalTimeInSeconds / 60;
        int seconds = totalTimeInSeconds % 60;
        return String.format("%d:%02d", minutes, seconds);
    }

    private int convertTotalTimeFromReadableToSeconds(String totalTimeInMinutes){
        String[] time = totalTimeInMinutes.split(":");
        return Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
    }

    private void updateTimeLeftLabel(){
        int currentTime = convertTotalTimeFromReadableToSeconds(timeLeftValue.getText());
        timeLeftValue.setText(convertTotalTimeFromSecondsToMinutes(currentTime - 1));
    }

    private void updateTimeRemainingLabel(){
        int currentTime = convertTotalTimeFromReadableToSeconds(remainingTimeLabel.getText());
        remainingTimeLabel.setText(convertTotalTimeFromSecondsToMinutes(currentTime - 1));
    }

    private void updateTimeElapsedLabel(){
        int currentTime = convertTotalTimeFromReadableToSeconds(elapsedTimeLabel.getText());
        elapsedTimeLabel.setText(convertTotalTimeFromSecondsToMinutes(currentTime + 1));
    }

    public JPanel getPanel() {
        return mainPanel;
    }





}
