package org.fokkittah.coffeeassistant.panelsGUI.brewing;

import org.fokkittah.coffeeassistant.panelsGUI.welcomeScreen.CardLayoutManager;

import javax.swing.*;

public class BrewingGui {
    private JPanel mainPanel;
    private JPanel leftSideControlPanel;
    private JLabel recipeLabel;
    private JPanel recipePanel;
    private JComboBox recipeComboBox;
    private JButton startButton;
    private JButton goBackButton;
    private JProgressBar brewingProgressBar;


    public BrewingGui(CardLayoutManager manager) {
        goBackButton.addActionListener(e -> manager.switchPanel("main"));
    }

    public JPanel getPanel() {
        return mainPanel;
    }

}
