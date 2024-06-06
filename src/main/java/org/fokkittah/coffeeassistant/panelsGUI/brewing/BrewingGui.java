package org.fokkittah.coffeeassistant.panelsGUI.brewing;

import org.fokkittah.coffeeassistant.configuration.SettingsService;
import org.fokkittah.coffeeassistant.configuration.recipe.Step;
import org.fokkittah.coffeeassistant.panelsGUI.welcomeScreen.CardLayoutManager;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


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
    private JList incomingStepsList;
    private JLabel currentStepLabel;
    private JLabel currentStepDescriptionLabel;
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
    private Timer timer;
    public static final int PROGRESS_BAR_MAX_VALUE = 100000;
    public static final int PROGRESS_BAR_REFRESH_DELAY = 10;
    public static int progressBarStep = 100;
    Iterator<Step> stepIterator;

    private SettingsService settingsService;


    public BrewingGui(CardLayoutManager manager, SettingsService settingsService) {
        this.settingsService = settingsService;
        goBackButton.addActionListener(e -> manager.switchPanel("main"));

        //move me to form ^^
        brewingProgressBar.setMaximum(PROGRESS_BAR_MAX_VALUE);

        startButton.addActionListener(e -> startProgressBar());
    }

    public JPanel getPanel() {
        return mainPanel;
    }


    //progress bar logic

    private final ActionListener progressBarUpdater = evt -> {
        if (brewingProgressBar.getValue() < PROGRESS_BAR_MAX_VALUE) {
            brewingProgressBar.setValue(brewingProgressBar.getValue() + progressBarStep);
        } else {
            brewingProgressBar.setValue(0);
            timer.stop();
            updateProgressBar();
        }
    };

    void startProgressBar(){
        List<Step> stepList = new ArrayList<>();
        //example, to be replaced with steps from recipe
        stepList.add(new Step(1, 60, "Bloom"));
        stepList.add(new Step(5, 240, "Brew on closed valve"));
        stepList.add(new Step(20, 240, "Brew on closed valve"));
        stepIterator = stepList.iterator();
        updateProgressBar();
    }

    void updateProgressBar(){
        if(stepIterator.hasNext()){
            Step currentStep = stepIterator.next();
            int stepDuration = currentStep.getDuration();
            progressBarStep = Math.toIntExact(PROGRESS_BAR_MAX_VALUE / PROGRESS_BAR_REFRESH_DELAY / stepDuration);
            timer = new Timer(PROGRESS_BAR_REFRESH_DELAY, progressBarUpdater);
            timer.start();
        }
    }

}
