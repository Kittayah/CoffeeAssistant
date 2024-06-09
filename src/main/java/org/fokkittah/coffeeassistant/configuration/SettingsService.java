package org.fokkittah.coffeeassistant.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.fokkittah.coffeeassistant.configuration.recipe.Recipe;

import java.io.File;
import java.io.IOException;

/**
 * This class is responsible for managing the application settings.
 * It provides methods to save and load settings from an XML file.
 * The settings include a list of coffee brewing recipes and grinder settings.
 * The service uses Jackson's XmlMapper for XML serialization and deserialization.
 */
public class SettingsService {

    ObjectMapper xmlMapper;
    private Settings settings;

    /**
     * Constructor for the SettingsService class.
     * Initializes the XmlMapper object used for XML serialization and deserialization.
     */
    public SettingsService() {
        xmlMapper = new XmlMapper();
    }

    /**
     * Saves the provided settings to the specified file path.
     * If the file does not exist, it is created.
     * @param settings The settings to save.
     * @param filePath The file path to save the settings to.
     * @throws IOException If an I/O error occurs.
     */
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

    /**
     * Loads the settings from the specified file path.
     * @param filePath The file path to load the settings from.
     * @return The loaded settings.
     * @throws IOException If an I/O error occurs.
     */
    public Settings loadSettings(String filePath) throws IOException {
        this.settings = xmlMapper.readValue(new File(filePath), Settings.class);
        return settings;
    }

    /**
     * Loads the settings from the default file path.
     * @return The loaded settings.
     */
    public Settings loadSettings(){
        try {
            String filePath = getDefaultFileName();
            System.out.println("LOADING SETTINGS FROM:" + filePath);
            return loadSettings(getDefaultFileName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Summarizes the total water used in a recipe.
     * @param recipe The recipe to summarize.
     * @return The total water used in the recipe.
     */
    public int summarizeTotalWaterInRecipe(Recipe recipe){
        int totalWater = 0;
        for (int i = 0; i < recipe.getSteps().size(); i++) {
            totalWater += recipe.getSteps().get(i).getWater();
        }
        return totalWater;
    }

    /**
     * Summarizes the total time used in a recipe.
     * @param recipe The recipe to summarize.
     * @return The total time used in the recipe in seconds.
     */
    public int summarizeTotalTimeInRecipeInSeconds(Recipe recipe){
        int totalTime = 0;
        for (int i = 0; i < recipe.getSteps().size(); i++) {
            totalTime += recipe.getSteps().get(i).getDuration();
        }
        return totalTime;
    }

    /**
     * Returns the default directory for the settings file.
     * @return The default directory for the settings file.
     */
    private String getDefaultDirectory() {
        return getUserDirectory() + File.separator + "coffeeAssistant";
    }

    /**
     * Returns the default file name for the settings file.
     * @return The default file name for the settings file.
     */
    private String getDefaultFileName() {
        return getDefaultDirectory() + File.separator + getUserName() + ".coffeebag";
    }

    /**
     * Saves the provided settings to the default file path.
     * @param settings The settings to save.
     */
    public void saveSettings(Settings settings){
        try {
            saveSettings(settings, getDefaultFileName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Saves the current settings to the default file path.
     */
    public void saveSettings(){
        try {
            saveSettings(this.settings, getDefaultFileName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Returns the user's directory.
     * @return The user's directory.
     */
    private String getUserDirectory() {
        return System.getProperty("user.dir");
    }

    /**
     * Returns the user's name.
     * @return The user's name.
     */
    private String getUserName() {
        return System.getProperty("user.name");
    }

    /**
     * Returns the current settings.
     * If the settings are not loaded, they are loaded from the default file path.
     * If the settings cannot be loaded from the default file path, they are loaded from the fallback file path.
     * @return The current settings.
     */
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

    /**
     * Sets the current settings.
     * @param settings The settings to set.
     */
    public void setSettings(Settings settings) {
        this.settings = settings;
    }
}