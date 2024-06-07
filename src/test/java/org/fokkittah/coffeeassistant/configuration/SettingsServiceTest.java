package org.fokkittah.coffeeassistant.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fokkittah.coffeeassistant.configuration.recipe.Recipe;
import org.fokkittah.coffeeassistant.configuration.recipe.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class SettingsServiceTest {

    @Mock
    private ObjectMapper xmlMapper;

    private SettingsService settingsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        settingsService = new SettingsService();
        settingsService.xmlMapper = xmlMapper;
    }

    @Test
    public void summarizeTotalWaterInRecipe() {
        Recipe recipe = new Recipe();
        recipe.setSteps(Arrays.asList(new Step(1, 2, "desc1"), new Step(3, 4, "desc2")));
        assertEquals(6, settingsService.summarizeTotalWaterInRecipe(recipe));
    }

    @Test
    public void summarizeTotalTimeInRecipeInSeconds() {
        Recipe recipe = new Recipe();
        recipe.setSteps(Arrays.asList(new Step(1, 2, "desc1"), new Step(3, 4, "desc2")));
        assertEquals(4, settingsService.summarizeTotalTimeInRecipeInSeconds(recipe));
    }

    @Test
    public void loadSettings() throws IOException {
        Settings expectedSettings = new Settings();
        when(xmlMapper.readValue(new File("src/test/resources/settings.xml"), Settings.class)).thenReturn(expectedSettings);
        Settings actualSettings = settingsService.loadSettings("src/test/resources/settings.xml");
        assertEquals(expectedSettings, actualSettings);
    }

    @Test
    public void saveSettingsToFile() throws IOException {
        Settings settings = new Settings();
        settingsService.saveSettings(settings, "src/test/resources/testSettings.xml");
        verify(xmlMapper, times(1)).writeValue(any(File.class), eq(settings));
    }

    @Test
    public void getSettingsReturnsExistingSettings() {
        Settings settings = new Settings();
        settingsService.setSettings(settings);
        assertEquals(settings, settingsService.getSettings());
    }

}