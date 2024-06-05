package org.fokkittah.coffeeassistant.configuration.grinder;

import java.util.List;
import java.util.Optional;

public class Grinder {

    public static final int DEFAULT_MIN_GRIND = 1;
    public static final int DEFAULT_MAX_GRIND = 30;

    String name;
    String description;
    List<GrindSettings> grindSettings;

    public Grinder() {
    }

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