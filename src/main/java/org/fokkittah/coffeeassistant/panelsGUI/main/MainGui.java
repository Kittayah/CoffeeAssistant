package org.fokkittah.coffeeassistant.panelsGUI.main;

import org.fokkittah.coffeeassistant.panelsGUI.welcomeScreen.CardLayoutManager;

import javax.swing.*;
;

public class MainGui {
    private JFrame frame;
    private JLabel welcomeMessage;
    private JPanel buttonBox;
    private JPanel mainPanel;
    private JButton manageRecipes;
    private JButton startBrewing;
    private JButton configureSettings;


    public MainGui(CardLayoutManager manager) {

        manageRecipes.addActionListener(e -> manager.switchPanel("recipes"));
        startBrewing.addActionListener(e -> manager.switchPanel("brewing"));
        configureSettings.addActionListener(e -> manager.switchPanel("settings"));

    }

    public JPanel getPanel() {
        return mainPanel;
    }

}
