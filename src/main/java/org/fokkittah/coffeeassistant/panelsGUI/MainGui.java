package org.fokkittah.coffeeassistant.panelsGUI;

import javax.swing.*;

public class MainGui {
    private JLabel welcomeMessage;
    private JPanel buttonBox;
    private JPanel mainPanel;
    private JButton manageRecipes;
    private JButton startBrewing;
    private JButton configureSettings;

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Coffee Assistant");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(mainPanel);

//        frame.getContentPane().add(welcomeMessage);

        frame.pack();
        frame.setVisible(true);
    }
}
