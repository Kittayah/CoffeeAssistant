package org.fokkittah.coffeeassistant.configuration.grinder;

import org.apache.commons.lang3.Range;

public class Grinder {
    String name;
    String description;

    Range<Integer> aeropressGrindRange;
    Range<Integer> espressoGrindRange;
    Range<Integer> mokaPotGrindRange;
    Range<Integer> v60GrindRange;
    Range<Integer> frenchPressGrindRange;

    public Grinder(String name, String description, Range<Integer> aeropressGrindRange, Range<Integer> espressoGrindRange, Range<Integer> mokaPotGrindRange, Range<Integer> v60GrindRange, Range<Integer> frenchPressGrindRange) {
        this.name = name;
        this.description = description;
        this.aeropressGrindRange = aeropressGrindRange;
        this.espressoGrindRange = espressoGrindRange;
        this.mokaPotGrindRange = mokaPotGrindRange;
        this.v60GrindRange = v60GrindRange;
        this.frenchPressGrindRange = frenchPressGrindRange;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Range<Integer> getAeropressGrindRange() {
        return aeropressGrindRange;
    }

    public Range<Integer> getEspressoGrindRange() {
        return espressoGrindRange;
    }

    public Range<Integer> getMokaPotGrindRange() {
        return mokaPotGrindRange;
    }

    public Range<Integer> getV60GrindRange() {
        return v60GrindRange;
    }

    public Range<Integer> getFrenchPressGrindRange() {
        return frenchPressGrindRange;
    }



    @Override
    public String toString() {
        return "Grinder{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", aeropressGrindRange=" + aeropressGrindRange +
                ", espressoGrindRange=" + espressoGrindRange +
                ", mokaPotGrindRange=" + mokaPotGrindRange +
                ", v60GrindRange=" + v60GrindRange +
                ", frenchPressGrindRange=" + frenchPressGrindRange +
                '}';
    }
}