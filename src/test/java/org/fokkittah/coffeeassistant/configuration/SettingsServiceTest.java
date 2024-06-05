package org.fokkittah.coffeeassistant.configuration;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class SettingsServiceTest {
    SettingsService settingsService = new SettingsService();

    @Test
    public void testService() throws IOException {
        Settings settings = settingsService.loadSettings("src/test/resources/settings.xml");
        settingsService.saveSettings(settings, "src/test/resources/settingsOutput.xml");
        Settings testLoad = settingsService.loadSettings("src/test/resources/settingsOutput.xml");
        System.out.println(settings);
    }
}
