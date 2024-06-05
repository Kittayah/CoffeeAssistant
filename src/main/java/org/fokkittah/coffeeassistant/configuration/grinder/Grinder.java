package org.fokkittah.coffeeassistant.configuration.grinder;

import java.util.List;

public class Grinder {
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

    @Override
    public String toString() {
        return "Grinder{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", grindSettings=" + grindSettings +
                '}';
    }
}