package org.fokkittah.coffeeassistant.panelsGUI.settings;

import org.fokkittah.coffeeassistant.configuration.Settings;
import org.fokkittah.coffeeassistant.configuration.SettingsService;
import org.fokkittah.coffeeassistant.configuration.grinder.GrindSettings;
import org.fokkittah.coffeeassistant.configuration.grinder.Grinder;
import org.fokkittah.coffeeassistant.panelsGUI.welcomeScreen.CardLayoutManager;
import org.fokkittah.coffeeassistant.utils.comboBoxGrinderRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SettingsGui {

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


    SettingsService settingsService = new SettingsService();

    public SettingsGui(CardLayoutManager manager){

        Settings settings = settingsService.getSettings();

        goBackButton.addActionListener(e -> manager.switchPanel("main"));

        // Initialize grinder combo box
        List<Grinder> grinders = settings.getGrinders();
        fillGrinderComboBox(grinders); //todo?
        grinderComboBox.setRenderer(new comboBoxGrinderRenderer());



        // Set grinder description
        grinderComboBox.addActionListener(e -> {
            Grinder selectedGrinder = (Grinder) grinderComboBox.getSelectedItem();
            setGrinderDescription(selectedGrinder.getDescription());
        });

//        // add button
//        addButton.addActionListener(e -> {settings.addGrinder();}); //todo
//
//        //set default grinder button
//        setDefaultGrinder.addActionListener(e -> {
//            currentSelectedGrinder = grinderComboBox.getSelectedItem();
//            grinderLoader.setDefaultGrinder(currentSelectedGrinder);
//        }); //todo
//
//        // User selects grinder
        grinderComboBox.addActionListener(e -> {
            Grinder selectedGrinder = (Grinder) grinderComboBox.getSelectedItem();

            GrindSettings aeropressSettings = selectedGrinder.getGrindSettingsByBrewer("aeropress");
            setAeropressMin(aeropressSettings.getMin());
            setAeropressMax(aeropressSettings.getMax());

            GrindSettings espressoSettings = selectedGrinder.getGrindSettingsByBrewer("espresso");
            setEspressoMin(espressoSettings.getMin());
            setEspressoMax(espressoSettings.getMax());

            GrindSettings mokaPotSettings = selectedGrinder.getGrindSettingsByBrewer("moka pot");
            setMokaPotMin(mokaPotSettings.getMin());
            setMokaPotMax(mokaPotSettings.getMax());

            GrindSettings frenchPressSettings = selectedGrinder.getGrindSettingsByBrewer("french press");
            setFrenchPressMin(frenchPressSettings.getMin());
            setFrenchPressMax(frenchPressSettings.getMax());

        });

    }

    public JPanel getPanel(){
        return mainPanel;
    }

    public void fillGrinderComboBox(List<Grinder> grinders){
        for (Grinder grinder : grinders) {
            grinderComboBox.addItem(grinder);
        }
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

