package org.fokkittah.coffeeassistant.panelsGUI.main;

import org.fokkittah.coffeeassistant.configuration.SettingsService;
import org.fokkittah.coffeeassistant.panelsGUI.welcomeScreen.CardLayoutManager;


import javax.swing.*;

public class MainGui {
    public static final String RECIPES = "recipes";
    public static final String BREWING = "brewing";
    public static final String SETTINGS = "settings";
    private JFrame frame;
    private JLabel welcomeMessage;
    private JPanel buttonBox;
    private JPanel mainPanel;
    private JButton manageRecipes;
    private JButton startBrewing;
    private JButton configureSettings;
    private SettingsService settingsService;

    public MainGui(CardLayoutManager manager, SettingsService settingsService) {

        this.settingsService = settingsService;

        manageRecipes.addActionListener(e -> manager.switchPanel(RECIPES));
        startBrewing.addActionListener(e -> manager.switchPanel(BREWING));
        configureSettings.addActionListener(e -> manager.switchPanel(SETTINGS));

//                           ╱|、
//                          (˚ˎ 。7
//                           |、˜〵
//                          じしˍ,)ノ

    }

    public JPanel getPanel() {
        return mainPanel;
    }

}
