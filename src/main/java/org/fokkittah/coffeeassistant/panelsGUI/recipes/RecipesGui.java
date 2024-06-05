package org.fokkittah.coffeeassistant.panelsGUI.recipes;

import org.fokkittah.coffeeassistant.panelsGUI.welcomeScreen.CardLayoutManager;

import javax.swing.*;

public class RecipesGui {
    private JPanel mainPanel;
    private JButton goBackButton;
    private JPanel controlPanel;
    private JButton saveButton;
    private JButton loadButton;
    private JButton newButton;
    private JPanel recipeEditPanel;
    private JLabel recipeName;
    private JTextField recipeInput;
    private JTextField coffeInput;
    private JLabel brewerLabel;
    private JLabel coffeeLabel;
    private JComboBox brewerComboBox;
    private JTextArea descriptionTextArea;
    private JLabel grindLabel;
    private JSpinner grindSpinner;
    private JLabel totalWaterLabel;
    private JLabel totalWaterSummaryLabel;
    private JLabel totalCoffeeAmountLabel;
    private JLabel totalCoffeAmountSummaryLabel;
    private JButton editStepsButton;

    public RecipesGui(CardLayoutManager manager) {
        goBackButton.addActionListener(e -> manager.switchPanel("main"));
    }

    public JPanel getPanel() {
        return mainPanel;
    }
}
