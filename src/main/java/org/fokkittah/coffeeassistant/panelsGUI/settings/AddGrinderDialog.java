package org.fokkittah.coffeeassistant.panelsGUI.settings;

import org.fokkittah.coffeeassistant.configuration.Settings;
import org.fokkittah.coffeeassistant.configuration.SettingsService;
import org.fokkittah.coffeeassistant.configuration.grinder.GrindSettings;
import org.fokkittah.coffeeassistant.configuration.grinder.Grinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;


public class AddGrinderDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JScrollPane descriptionScrollPanel;
    private JTextArea descriptionTextArea;
    private JPanel mainPanel;
    private JPanel controlPanel;
    private JLabel grinderNameLabel;
    private JTextField grinderNameTextField;
    private JPanel grindSettingsPanel;
    private JLabel aeropressLabel;
    private JLabel espressoLabel;
    private JLabel mokaPotLabel;
    private JLabel frenchPressLabel;
    private JSpinner aeropressMinSpinner;
    private JSpinner aeropressMaxSpinner;
    private JSpinner espressoMinSpinner;
    private JSpinner espressoMaxSpinner;
    private JSpinner mokaPotMinSpinner;
    private JSpinner mokaPotMaxSpinner;
    private JSpinner frenchPressMinSpinner;
    private JSpinner frenchPressMaxSpinner;
    private JLabel recommendedLabel;
    private JLabel descriptionLabel;

    SettingsService settingsService;

    public AddGrinderDialog(SettingsService settingsService) {

        this.settingsService = settingsService;

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        aeropressMinSpinner.setModel(new SpinnerNumberModel(1, 1, 30, 1));
        aeropressMaxSpinner.setModel(new SpinnerNumberModel(1, 1, 30, 1));
        espressoMinSpinner.setModel(new SpinnerNumberModel(1, 1, 30, 1));
        espressoMaxSpinner.setModel(new SpinnerNumberModel(1, 1, 30, 1));
        mokaPotMinSpinner.setModel(new SpinnerNumberModel(1, 1, 30, 1));
        mokaPotMaxSpinner.setModel(new SpinnerNumberModel(1, 1, 30, 1));
        frenchPressMinSpinner.setModel(new SpinnerNumberModel(1, 1, 30, 1));
        frenchPressMaxSpinner.setModel(new SpinnerNumberModel(1, 1, 30, 1));
    }

    private void onOK() {
        // add your code here
        if (grinderNameTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a name for the grinder");
            return;
        }
        if (descriptionTextArea.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a description for the grinder");
            return;
        }
        if (isMinMaxSame(aeropressMinSpinner, aeropressMaxSpinner, "Aeropress") ||
                isMinMaxSame(espressoMinSpinner, espressoMaxSpinner, "Espresso") ||
                isMinMaxSame(mokaPotMinSpinner, mokaPotMaxSpinner, "Moka Pot") ||
                isMinMaxSame(frenchPressMinSpinner, frenchPressMaxSpinner, "French Press")) {
            return;
        }

        Grinder grinder = new Grinder();

        grinder.setName(grinderNameTextField.getText());
        grinder.setDescription(descriptionTextArea.getText());

        grinder.getGrindSettings().add(new GrindSettings("Aeropress", (int) aeropressMinSpinner.getValue(), (int) aeropressMaxSpinner.getValue()));
        grinder.getGrindSettings().add(new GrindSettings("Espresso", (int) espressoMinSpinner.getValue(), (int) espressoMaxSpinner.getValue()));
        grinder.getGrindSettings().add(new GrindSettings("Moka Pot", (int) mokaPotMinSpinner.getValue(), (int) mokaPotMaxSpinner.getValue()));
        grinder.getGrindSettings().add(new GrindSettings("French Press", (int) frenchPressMinSpinner.getValue(), (int) frenchPressMaxSpinner.getValue()));

        settingsService.getSettings().getGrinders().add(grinder);
        settingsService.saveSettings();
        this.revalidate();
        this.repaint();
        SwingUtilities.updateComponentTreeUI(mainPanel.getRootPane());
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        AddGrinderDialog dialog = new AddGrinderDialog(new SettingsService());
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private boolean isMinMaxSame(JSpinner minSpinner, JSpinner maxSpinner, String settingName) {
        if (minSpinner.getValue().equals(maxSpinner.getValue())) {
            JOptionPane.showMessageDialog(this, settingName + " min and max grind settings cannot be the same");
            return true;
        }
        return false;
    }

}
