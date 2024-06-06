package org.fokkittah.coffeeassistant.panelsGUI.settings;

import org.fokkittah.coffeeassistant.configuration.SettingsService;
import org.fokkittah.coffeeassistant.configuration.grinder.GrindSettings;
import org.fokkittah.coffeeassistant.configuration.grinder.Grinder;
import org.fokkittah.coffeeassistant.panelsGUI.welcomeScreen.CardLayoutManager;
import org.fokkittah.coffeeassistant.utils.comboBoxGrinderRenderer;
import javax.swing.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.Collections;
import java.util.List;
import java.util.Vector;



public class SettingsGui {

    public static final String AEROPRESS = "aeropress";
    public static final String ESPRESSO = "espresso";
    public static final String MOKA_POT = "moka pot";
    public static final String FRENCH_PRESS = "french press";
    private JPanel mainPanel;
    private JLabel setGrinderLabel;
    private JComboBox grinderComboBox;
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

    SettingsService settingsService;

    public SettingsGui(CardLayoutManager manager, SettingsService settingsService){
        this.settingsService = settingsService;

        goBackButton.addActionListener(e -> manager.switchPanel("main"));

        // Initialize grinder combo box
        fillGrinderComboBox(settingsService.getSettings().getGrinders());
        grinderComboBox.setRenderer(new comboBoxGrinderRenderer());



        // Set grinder description
        grinderComboBox.addActionListener(e -> {
            Grinder selectedGrinder = (Grinder) grinderComboBox.getSelectedItem();
            setGrinderDescription(selectedGrinder.getDescription());
        });

        // add button
        addButton.addActionListener(e -> {
            AddGrinderDialog addGrinderDialog = new AddGrinderDialog(settingsService);
            addGrinderDialog.pack();
            addGrinderDialog.setVisible(true);
            addGrinderDialog.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent event){
                    fillGrinderComboBox(settingsService.getSettings().getGrinders());
                    grinderComboBox.setRenderer(new comboBoxGrinderRenderer());
                }
            });
//            SwingUtilities.updateComponentTreeUI(mainPanel.getRootPane());
        }); //todo
//
        //set default grinder button
        setDefaultGrinder.addActionListener(e -> {
            List<Grinder> grinders = settingsService.getSettings().getGrinders();
            Grinder currentSelectedGrinder = (Grinder) grinderComboBox.getSelectedItem();
            grinders.remove(currentSelectedGrinder);
            grinders.addFirst(currentSelectedGrinder);
            settingsService.saveSettings();
        }); //todo

//        // User selects grinder
        grinderComboBox.addActionListener(e -> {
            Grinder selectedGrinder = (Grinder) grinderComboBox.getSelectedItem();

            GrindSettings aeropressSettings = selectedGrinder.getGrindSettingsByBrewer(AEROPRESS);
            setAeropressMin(aeropressSettings.getMin());
            setAeropressMax(aeropressSettings.getMax());

            GrindSettings espressoSettings = selectedGrinder.getGrindSettingsByBrewer(ESPRESSO);
            setEspressoMin(espressoSettings.getMin());
            setEspressoMax(espressoSettings.getMax());

            GrindSettings mokaPotSettings = selectedGrinder.getGrindSettingsByBrewer(MOKA_POT);
            setMokaPotMin(mokaPotSettings.getMin());
            setMokaPotMax(mokaPotSettings.getMax());

            GrindSettings frenchPressSettings = selectedGrinder.getGrindSettingsByBrewer(FRENCH_PRESS);
            setFrenchPressMin(frenchPressSettings.getMin());
            setFrenchPressMax(frenchPressSettings.getMax());

        });

    }

    public JPanel getPanel(){
        return mainPanel;
    }

    public void fillGrinderComboBox(List<Grinder> grinders){
        grinderComboBox.setModel(new DefaultComboBoxModel<>(new Vector<>(grinders)));
    }

    public void setGrinderDescription(String description){
        grinderDescriptionTextArea.setText(description);
    }

    public void setGrinderComboBox(String grinderName){
        grinderComboBox.setSelectedItem(grinderName);
    }

    public String getSelectedGrinder(){
        return (String) grinderComboBox.getSelectedItem();
    }

    public void setAeropressMin(int min){
        aeropressMin.setText(String.valueOf(min));
    }

    public void setAeropressMax(int max){
        aeropressMax.setText(String.valueOf(max));
    }

    public void setEspressoMin(int min){
        espressoMin.setText(String.valueOf(min));
    }

    public void setEspressoMax(int max){
        espressoMax.setText(String.valueOf(max));
    }

    public void setMokaPotMin(int min){
        mokaPotMin.setText(String.valueOf(min));
    }

    public void setMokaPotMax(int max){
        mokaPotMax.setText(String.valueOf(max));
    }

    public void setFrenchPressMin(int min){
        frenchPressMin.setText(String.valueOf(min));
    }

    public void setFrenchPressMax(int max){
        frenchPressMax.setText(String.valueOf(max));
    }


}

