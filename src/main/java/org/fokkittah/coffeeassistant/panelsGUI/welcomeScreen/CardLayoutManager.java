package org.fokkittah.coffeeassistant.panelsGUI.welcomeScreen;

import org.fokkittah.coffeeassistant.panelsGUI.brewing.BrewingGui;
import org.fokkittah.coffeeassistant.panelsGUI.main.MainGui;
import org.fokkittah.coffeeassistant.panelsGUI.recipes.RecipesGui;
import org.fokkittah.coffeeassistant.panelsGUI.settings.SettingsGui;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class CardLayoutManager extends JFrame{
    
    private JPanel cardsPanel;

    public CardLayoutManager() {
        setTitle("Coffee Assistant");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MainGui mainGui = new MainGui(this);
        BrewingGui brewingGui = new BrewingGui(this);
        SettingsGui settingsGui = new SettingsGui(this);
        RecipesGui recipesGui = new RecipesGui(this);

        cardsPanel.add(mainGui.getPanel(), "main");
        cardsPanel.add(brewingGui.getPanel(), "brewing");
        cardsPanel.add(settingsGui.getPanel(), "settings");
        cardsPanel.add(recipesGui.getPanel(), "recipes");

        setContentPane(cardsPanel);
        pack();
        setVisible(true);

        switchPanel("main");

    }

    public void switchPanel(String panelName){
        CardLayout appCards = (CardLayout) cardsPanel.getLayout();
        appCards.show(cardsPanel, panelName);
    }

}
