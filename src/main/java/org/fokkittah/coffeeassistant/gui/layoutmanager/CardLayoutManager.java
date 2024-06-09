package org.fokkittah.coffeeassistant.gui.layoutmanager;

import org.fokkittah.coffeeassistant.configuration.SettingsService;
import org.fokkittah.coffeeassistant.gui.brewing.BrewingGui;
import org.fokkittah.coffeeassistant.gui.main.MainGui;
import org.fokkittah.coffeeassistant.gui.recipes.RecipesGui;
import org.fokkittah.coffeeassistant.gui.settings.SettingsGui;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;


/**
 * Manages the layout and switching of different panels in the Coffee Assistant application.
 */
@Component
public class CardLayoutManager extends JFrame {
    
    private JPanel cardsPanel;
    private SettingsService settingsService = new SettingsService();

    public CardLayoutManager() {
        setTitle("Coffee Assistant");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MainGui mainGui = new MainGui(this, settingsService);
        BrewingGui brewingGui = new BrewingGui(this, settingsService);
        SettingsGui settingsGui = new SettingsGui(this, settingsService);
        RecipesGui recipesGui = new RecipesGui(this, settingsService);

        cardsPanel.add(mainGui.getPanel(), PanelName.MAIN.name());
        cardsPanel.add(brewingGui.getPanel(), PanelName.BREWING.name());
        cardsPanel.add(settingsGui.getPanel(), PanelName.SETTINGS.name());
        cardsPanel.add(recipesGui.getPanel(), PanelName.RECIPES.name());

        setContentPane(cardsPanel);
        pack();
        setVisible(true);

        switchPanel(PanelName.MAIN);
    }

    /**
     * Switches to the specified panel.
     *
     * @param panelName The name of the panel to switch to.
     */
    public void switchPanel(PanelName panelName) {
        CardLayout appCards = (CardLayout) cardsPanel.getLayout();
        appCards.show(cardsPanel, panelName.name());
    }

    public enum PanelName {
        MAIN,
        BREWING,
        SETTINGS,
        RECIPES;
    }

}



//⠀⠀⠀⠀⠀⠀⠀        ⠀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
//        ⠀⠀⠀⠀⠀⠀⣰⣿⣿⣿⣿⣦⣀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
//        ⠀⠀⠀⠀⠀⠀⢿⣿⠟⠋⠉⠀⠀⠀⠀⠉⠑⠢⣄⡀⠀⠀⠀⠀⠀
//        ⠀⠀⠀⠀⠀⢠⠞⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢿⣿⣿⣦⡀
//        ⠀⣀⠀⠀⢀⡏⠀⢀⣴⣶⣶⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⣿⣿⠇
//        ⣾⣿⣿⣦⣼⡀⠀⢺⣿⣿⡿⠃⠀⠀⠀⠀⣠⣤⣄⠀⠀⠈⡿⠋⠀
//        ⢿⣿⣿⣿⣿⣇⠀⠤⠌⠁⠀⡀⢲⡶⠄⢸⣏⣿⣿⠀⠀⠀⡇⠀⠀
//        ⠈⢿⣿⣿⣿⣿⣷⣄⡀⠀⠀⠈⠉⠓⠂⠀⠙⠛⠛⠠⠀⡸⠁⠀⠀
//        ⠀⠀⠻⣿⣿⣿⣿⣿⣿⣷⣦⣄⣀⠀⠀⠀⠀⠑⠀⣠⠞⠁⠀⠀⠀
//        ⠀⠀⠀⢸⡏⠉⠛⠛⠛⠿⠿⣿⣿⣿⣿⣿⣿⣿⣿⡄⠀⠀⠀⠀⠀
//        ⠀⠀⠀⠸⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠛⢿⣿⣿⣿⣿⡄⠀⠀⠀⠀
//        ⠀⠀⠀⢷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢻⣿⣿⣿⣿⡀⠀⠀⠀
//        ⠀⠀⠀⢸⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⡇⠀⠀⠀
//        ⠀⠀⠀⢸⣿⣦⣀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣼⡟⠻⠿⠟⠀⠀⠀⠀
//        ⠀⠀⠀⠀⣿⣿⣿⣿⣶⠶⠤⠤⢤⣶⣾⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀
//        ⠀⠀⠀⠀⠹⣿⣿⣿⠏⠀⠀⠀⠈⢿⣿⣿⡿⠁⠀⠀⠀⠀⠀⠀⠀
//        ⠀⠀⠀⠀⠀⠈⠉⠉⠀⠀⠀⠀⠀⠀⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀



