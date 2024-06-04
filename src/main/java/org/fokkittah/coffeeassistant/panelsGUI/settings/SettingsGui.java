package org.fokkittah.coffeeassistant.panelsGUI.settings;

import org.fokkittah.coffeeassistant.panelsGUI.welcomeScreen.CardLayoutManager;

import javax.swing.*;

public class SettingsGui {

    private JPanel mainPanel;
    private JLabel setGrinderLabel;
    private JComboBox grinderComboBox;
    private JPanel grinderSelectPanel;
    private JButton goBackButton;

    public SettingsGui(CardLayoutManager manager){
        goBackButton.addActionListener(e -> manager.switchPanel("main"));
    }

    public JPanel getPanel(){
        return mainPanel;
    }
}

