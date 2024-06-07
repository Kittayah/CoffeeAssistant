package org.fokkittah.coffeeassistant.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.fokkittah.coffeeassistant.configuration.recipe.Recipe;

import java.io.File;

import java.io.IOException;


public class SettingsService {

    ObjectMapper xmlMapper;
    private Settings settings;

    public SettingsService() {
        xmlMapper = new XmlMapper();
    }

    public void saveSettings(Settings settings, String filePath) throws IOException {
        File file = new File(filePath);
        File dir = new File(getDefaultDirectory());
        if(!dir.exists()){
            dir.mkdir();
        }
        if(!file.exists()){
            file.createNewFile();
        }
        this.settings = settings;
        xmlMapper.writeValue(file, settings);
        System.out.println("SAVED: " + file.getPath());
    }

    public Settings loadSettings(String filePath) throws IOException {
        this.settings = xmlMapper.readValue(new File(filePath), Settings.class);
        return settings;
    }

    public Settings loadSettings(){
        try {
            String filePath = getDefaultFileName();
            System.out.println("LOADING SETTINGS FROM:" + filePath);
            return loadSettings(getDefaultFileName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int summarizeTotalWaterInRecipe(Recipe recipe){
        int totalWater = 0;
        for (int i = 0; i < recipe.getSteps().size(); i++) {
            totalWater += recipe.getSteps().get(i).getWater();
        }
        return totalWater;
    }

    public int summarizeTotalTimeInRecipeInSeconds(Recipe recipe){
        int totalTime = 0;
        for (int i = 0; i < recipe.getSteps().size(); i++) {
            totalTime += recipe.getSteps().get(i).getDuration();
        }
        return totalTime;
    }

    private String getDefaultDirectory() {
        return getUserDirectory() + File.separator + "coffeeAssistant";
    }

    private String getDefaultFileName() {
        return getDefaultDirectory() + File.separator + getUserName() + ".coffeebag";
    }

    public void saveSettings(Settings settings){
        try {
            saveSettings(settings, getDefaultFileName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveSettings(){
        try {
            saveSettings(this.settings, getDefaultFileName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private String getUserDirectory() {
        return System.getProperty("user.dir");
    }

    private String getUserName() {
        return System.getProperty("user.name");
    }

    public Settings getSettings() {
        if(settings==null){
            try {
                settings = loadSettings();
            } catch (RuntimeException ex){
                try {
                    settings = loadSettings("src/main/resources/settings.xml");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }
}
