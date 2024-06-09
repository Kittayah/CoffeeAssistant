package org.fokkittah.coffeeassistant.gui.settings;

import org.fokkittah.coffeeassistant.configuration.SettingsService;
import org.fokkittah.coffeeassistant.configuration.grinder.GrindSettings;
import org.fokkittah.coffeeassistant.configuration.grinder.Grinder;

import javax.swing.*;
import java.awt.event.*;

/**
 * This class provides a dialog for adding a new grinder in the Coffee Assistant application.
 * It allows users to enter the grinder's name, description, and grind settings for various brewing methods.
 */
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

    private final SettingsService settingsService;

    /**
     * Constructs an AddGrinderDialog.
     *
     * @param settingsService The SettingsService to manage application settings.
     */
    public AddGrinderDialog(SettingsService settingsService) {
        this.settingsService = settingsService;

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        // Add action listeners for buttons
        buttonOK.addActionListener(e -> onOK());
        buttonCancel.addActionListener(e -> onCancel());

        // Handle window closing event
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // Handle escape key press
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        // Initialize spinner models
        initializeSpinners();
    }

    /**
     * Initializes the spinner models with default values.
     */
    private void initializeSpinners() {
        aeropressMinSpinner.setModel(new SpinnerNumberModel(1, 1, 30, 1));
        aeropressMaxSpinner.setModel(new SpinnerNumberModel(1, 1, 30, 1));
        espressoMinSpinner.setModel(new SpinnerNumberModel(1, 1, 30, 1));
        espressoMaxSpinner.setModel(new SpinnerNumberModel(1, 1, 30, 1));
        mokaPotMinSpinner.setModel(new SpinnerNumberModel(1, 1, 30, 1));
        mokaPotMaxSpinner.setModel(new SpinnerNumberModel(1, 1, 30, 1));
        frenchPressMinSpinner.setModel(new SpinnerNumberModel(1, 1, 30, 1));
        frenchPressMaxSpinner.setModel(new SpinnerNumberModel(1, 1, 30, 1));
    }

    /**
     * Handles the OK button action.
     * Validates the input and adds the new grinder to the settings.
     */
    private void onOK() {
        if (validateInput()) {
            Grinder grinder = createGrinder();
            settingsService.getSettings().getGrinders().add(grinder);
            settingsService.saveSettings();
            dispose();
        }
    }

    /**
     * Validates the user input.
     *
     * @return True if the input is valid, false otherwise.
     */
    private boolean validateInput() {
        if (grinderNameTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a name for the grinder");
            return false;
        }
        if (descriptionTextArea.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a description for the grinder");
            return false;
        }
        if (isMinMaxSame(aeropressMinSpinner, aeropressMaxSpinner, "Aeropress") ||
                isMinMaxSame(espressoMinSpinner, espressoMaxSpinner, "Espresso") ||
                isMinMaxSame(mokaPotMinSpinner, mokaPotMaxSpinner, "Moka Pot") ||
                isMinMaxSame(frenchPressMinSpinner, frenchPressMaxSpinner, "French Press")) {
            return false;
        }
        return true;
    }

    /**
     * Creates a new Grinder object from the user input.
     *
     * @return The created Grinder object.
     */
    private Grinder createGrinder() {
        Grinder grinder = new Grinder();
        grinder.setName(grinderNameTextField.getText());
        grinder.setDescription(descriptionTextArea.getText());

        grinder.getGrindSettings().add(new GrindSettings("Aeropress", (int) aeropressMinSpinner.getValue(), (int) aeropressMaxSpinner.getValue()));
        grinder.getGrindSettings().add(new GrindSettings("Espresso", (int) espressoMinSpinner.getValue(), (int) espressoMaxSpinner.getValue()));
        grinder.getGrindSettings().add(new GrindSettings("Moka Pot", (int) mokaPotMinSpinner.getValue(), (int) mokaPotMaxSpinner.getValue()));
        grinder.getGrindSettings().add(new GrindSettings("French Press", (int) frenchPressMinSpinner.getValue(), (int) frenchPressMaxSpinner.getValue()));

        return grinder;
    }

    /**
     * Checks if the min and max values for a grind setting are the same.
     *
     * @param minSpinner The spinner for the minimum value.
     * @param maxSpinner The spinner for the maximum value.
     * @param settingName The name of the grind setting.
     * @return True if the min and max values are the same, false otherwise.
     */
    private boolean isMinMaxSame(JSpinner minSpinner, JSpinner maxSpinner, String settingName) {
        if (minSpinner.getValue().equals(maxSpinner.getValue())) {
            JOptionPane.showMessageDialog(this, settingName + " min and max grind settings cannot be the same");
            return true;
        }
        return false;
    }

    /**
     * Handles the cancel button action.
     * Disposes of the dialog.
     */
    private void onCancel() {
        dispose();
    }

    /**
     * Main method to test the AddGrinderDialog.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        AddGrinderDialog dialog = new AddGrinderDialog(new SettingsService());
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
