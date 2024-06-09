package org.fokkittah.coffeeassistant.gui.main;

import org.fokkittah.coffeeassistant.configuration.SettingsService;
import org.fokkittah.coffeeassistant.gui.layoutmanager.CardLayoutManager;


import javax.swing.*;

/**
 * This class represents the main GUI for the CoffeeAssistant application.
 * It includes components such as buttons and panels, and provides methods to get the main panel.
 * It also includes action listeners for the buttons to switch between different panels.
 */
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
        manageRecipes.addActionListener(e -> manager.switchPanel(CardLayoutManager.PanelName.RECIPES));
        startBrewing.addActionListener(e -> manager.switchPanel(CardLayoutManager.PanelName.BREWING));
        configureSettings.addActionListener(e -> manager.switchPanel(CardLayoutManager.PanelName.SETTINGS));

    }

    public JPanel getPanel() {
        return mainPanel;
    }

}

//                           ╱|、
//                          (˚ˎ 。7
//                           |、˜〵
//                          じしˍ,)ノ
