package org.fokkittah.coffeeassistant.configuration.grinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class represents a coffee grinder.
 * It includes properties such as name, description, and a list of grind settings.
 * It provides methods to get and set these properties, and to get grind settings by brewer.
 */
public class Grinder {

    public static final int DEFAULT_MIN_GRIND = 1;
    public static final int DEFAULT_MAX_GRIND = 30;

    String name;
    String description;
    List<GrindSettings> grindSettings;

    public Grinder() {
        grindSettings = new ArrayList<>();
    }

    // Getters and setters for the Grinder class properties.
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<GrindSettings> getGrindSettings() {
        return grindSettings;
    }

    public void setGrindSettings(List<GrindSettings> grindSettings) {
        this.grindSettings = grindSettings;
    }

    /**
     * Returns the grind settings for a specific brewer.
     * If the grind settings for the brewer are not found, default grind settings are returned.
     * @param brewerName The name of the brewer.
     * @return The grind settings for the brewer.
     */
    public GrindSettings getGrindSettingsByBrewer(String brewerName){
        Optional<GrindSettings> settings = this.grindSettings.stream().filter(gr -> gr.brewingMethod.equalsIgnoreCase(brewerName)).findFirst();
        return settings.orElseGet(() -> new GrindSettings(brewerName, DEFAULT_MIN_GRIND, DEFAULT_MAX_GRIND));
    }

    @Override
    public String toString() {
        return "Grinder{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", grindSettings=" + grindSettings +
                '}';
    }
}