package org.fokkittah.coffeeassistant.panelsGUI.recipes;

import org.fokkittah.coffeeassistant.panelsGUI.welcomeScreen.CardLayoutManager;

import javax.swing.*;

public class RecipesGui {
    private JPanel mainPanel;
    private JButton goBackButton;

    public RecipesGui(CardLayoutManager manager) {
        goBackButton.addActionListener(e -> manager.switchPanel("main"));
    }

    public JPanel getPanel() {
        return mainPanel;
    }
}
