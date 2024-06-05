package org.fokkittah.coffeeassistant.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class SettingsService {

    ObjectMapper xmlMapper;
    private Settings settings;

    public SettingsService() {
        xmlMapper = new XmlMapper();
    }

    public void saveSettings(Settings settings, String filePath) throws IOException {
        xmlMapper.writeValue(new File(filePath), settings);
    }

    public Settings loadSettings(String filePath) throws IOException {
        return xmlMapper.readValue(new File(filePath), Settings.class);
    }

    public Settings loadSettings(){
        try {
            return loadSettings(getDefaultFileName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getDefaultFileName() {
        return getUserDirectory() + "/coffeeAssistant/" + getUserName() + ".coffeebag";
    }

    public void saveSettings(Settings settings){
        try {
            saveSettings(settings, getDefaultFileName());
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
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }
}
