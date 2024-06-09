package org.fokkittah.coffeeassistant.gui.settings;

import org.fokkittah.coffeeassistant.configuration.SettingsService;
import org.fokkittah.coffeeassistant.configuration.grinder.GrindSettings;
import org.fokkittah.coffeeassistant.configuration.grinder.Grinder;
import org.fokkittah.coffeeassistant.gui.layoutmanager.CardLayoutManager;
import org.fokkittah.coffeeassistant.utils.ComboBoxGrinderRenderer;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Vector;

/**
 * This class provides the GUI for managing grinder settings in the Coffee Assistant application.
 * It allows users to select, add, and set default grinders, as well as view recommended settings.
 */
public class SettingsGui {

    public static final String AEROPRESS = "aeropress";
    public static final String ESPRESSO = "espresso";
    public static final String MOKA_POT = "moka pot";
    public static final String FRENCH_PRESS = "french press";

    private JPanel mainPanel;
    private JLabel setGrinderLabel;
    private JComboBox<Grinder> grinderComboBox;
    private JPanel grinderSelectPanel;
    private JButton goBackButton;
    private JButton addButton;
    private JButton setDefaultGrinder;
    private JTextArea grinderDescriptionTextArea;
    private JLabel aeropressLabel;
    private JLabel recommendedLabel;
    private JLabel espressoLabel;
    private JLabel mokaPotLabel;
    private JLabel frenchPressLabel;
    private JLabel aeropressMin;
    private JLabel aeropressMax;
    private JLabel espressoMin;
    private JLabel espressoMax;
    private JLabel mokaPotMin;
    private JLabel mokaPotMax;
    private JLabel frenchPressMin;
    private JLabel frenchPressMax;
    private JScrollPane descriptionScrollPane;

    private final SettingsService settingsService;

    /**
     * Constructs a SettingsGui object.
     *
     * @param manager        The CardLayoutManager to manage panel switching.
     * @param settingsService The SettingsService to manage application settings.
     */
    public SettingsGui(CardLayoutManager manager, SettingsService settingsService) {
        this.settingsService = settingsService;

        goBackButton.addActionListener(e -> manager.switchPanel(CardLayoutManager.PanelName.MAIN));

        // Initialize grinder combo box
        fillGrinderComboBox(settingsService.getSettings().getGrinders());
        grinderComboBox.setRenderer(new ComboBoxGrinderRenderer());

        // Set grinder description on selection
        grinderComboBox.addActionListener(e -> {
            Grinder selectedGrinder = (Grinder) grinderComboBox.getSelectedItem();
            setGrinderDescription(selectedGrinder.getDescription());
        });

        // Add new grinder
        addButton.addActionListener(e -> {
            AddGrinderDialog addGrinderDialog = new AddGrinderDialog(settingsService);
            addGrinderDialog.pack();
            addGrinderDialog.setVisible(true);
            addGrinderDialog.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent event) {
                    fillGrinderComboBox(settingsService.getSettings().getGrinders());
                    grinderComboBox.setRenderer(new ComboBoxGrinderRenderer());
                }
            });
        });

        // Set default grinder
        setDefaultGrinder.addActionListener(e -> {
            List<Grinder> grinders = settingsService.getSettings().getGrinders();
            Grinder currentSelectedGrinder = (Grinder) grinderComboBox.getSelectedItem();
            grinders.remove(currentSelectedGrinder);
            grinders.add(0, currentSelectedGrinder);
            settingsService.saveSettings();
        });

        // Update grind settings based on selected grinder
        grinderComboBox.addActionListener(e -> {
            Grinder selectedGrinder = (Grinder) grinderComboBox.getSelectedItem();
            updateGrindSettings(selectedGrinder);
        });
    }

    /**
     * Updates the grind settings display based on the selected grinder.
     *
     * @param selectedGrinder The selected grinder.
     */
    private void updateGrindSettings(Grinder selectedGrinder) {
        setGrindSettings(selectedGrinder, AEROPRESS, aeropressMin, aeropressMax);
        setGrindSettings(selectedGrinder, ESPRESSO, espressoMin, espressoMax);
        setGrindSettings(selectedGrinder, MOKA_POT, mokaPotMin, mokaPotMax);
        setGrindSettings(selectedGrinder, FRENCH_PRESS, frenchPressMin, frenchPressMax);
    }

    /**
     * Sets the grind settings for a specific brewing method.
     *
     * @param grinder   The grinder object.
     * @param method    The brewing method.
     * @param minLabel  The label to display the minimum grind setting.
     * @param maxLabel  The label to display the maximum grind setting.
     */
    private void setGrindSettings(Grinder grinder, String method, JLabel minLabel, JLabel maxLabel) {
        GrindSettings settings = grinder.getGrindSettingsByBrewer(method);
        minLabel.setText(String.valueOf(settings.getMin()));
        maxLabel.setText(String.valueOf(settings.getMax()));
    }

    /**
     * Returns the main panel of the Settings GUI.
     *
     * @return The main panel.
     */
    public JPanel getPanel() {
        return mainPanel;
    }

    /**
     * Fills the grinder combo box with the provided list of grinders.
     *
     * @param grinders The list of grinders to populate the combo box.
     */
    public void fillGrinderComboBox(List<Grinder> grinders) {
        grinderComboBox.setModel(new DefaultComboBoxModel<>(new Vector<>(grinders)));
    }

    /**
     * Sets the grinder description text area.
     *
     * @param description The description of the selected grinder.
     */
    public void setGrinderDescription(String description) {
        grinderDescriptionTextArea.setText(description);
    }

    /**
     * Sets the selected grinder in the combo box.
     *
     * @param grinderName The name of the grinder to select.
     */
    public void setGrinderComboBox(String grinderName) {
        grinderComboBox.setSelectedItem(grinderName);
    }

    /**
     * Gets the name of the selected grinder from the combo box.
     *
     * @return The name of the selected grinder.
     */
    public String getSelectedGrinder() {
        return (String) grinderComboBox.getSelectedItem();
    }

    /**
     * Sets the minimum grind setting for Aeropress.
     *
     * @param min The minimum grind setting.
     */
    public void setAeropressMin(int min) {
        aeropressMin.setText(String.valueOf(min));
    }

    /**
     * Sets the maximum grind setting for Aeropress.
     *
     * @param max The maximum grind setting.
     */
    public void setAeropressMax(int max) {
        aeropressMax.setText(String.valueOf(max));
    }

    /**
     * Sets the minimum grind setting for Espresso.
     *
     * @param min The minimum grind setting.
     */
    public void setEspressoMin(int min) {
        espressoMin.setText(String.valueOf(min));
    }

    /**
     * Sets the maximum grind setting for Espresso.
     *
     * @param max The maximum grind setting.
     */
    public void setEspressoMax(int max) {
        espressoMax.setText(String.valueOf(max));
    }

    /**
     * Sets the minimum grind setting for Moka Pot.
     *
     * @param min The minimum grind setting.
     */
    public void setMokaPotMin(int min) {
        mokaPotMin.setText(String.valueOf(min));
    }

    /**
     * Sets the maximum grind setting for Moka Pot.
     *
     * @param max The maximum grind setting.
     */
    public void setMokaPotMax(int max) {
        mokaPotMax.setText(String.valueOf(max));
    }

    /**
     * Sets the minimum grind setting for French Press.
     *
     * @param min The minimum grind setting.
     */
    public void setFrenchPressMin(int min) {
        frenchPressMin.setText(String.valueOf(min));
    }

    /**
     * Sets the maximum grind setting for French Press.
     *
     * @param max The maximum grind setting.
     */
    public void setFrenchPressMax(int max) {
        frenchPressMax.setText(String.valueOf(max));
    }
}
