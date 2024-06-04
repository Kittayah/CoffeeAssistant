package org.fokkittah.coffeeassistant.configuration.grinder;

import org.apache.commons.configuration2.HierarchicalConfiguration;
import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.tree.ImmutableNode;
import org.apache.commons.lang3.Range;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GrinderLoader {

    public List<Grinder> loadAllGrinders(String filePath) {
        Configurations configs = new Configurations();
        XMLConfiguration grinderXml;

        try {
            grinderXml = configs.xml(new File(filePath));
            List<HierarchicalConfiguration<ImmutableNode>> grinderNodes = grinderXml.configurationsAt("grinder");

            List<Grinder> allGrinders = new ArrayList<>();

            for (HierarchicalConfiguration g : grinderNodes) {

                String name = g.getString("name");
                String description = g.getString("description");
                Integer aeropressGrindMin = g.getInt("aeropressGrind.min");
                Integer aeropressGrindMax = g.getInt("aeropressGrind.max");
                Integer espressoGrindMin = g.getInt("espressoGrind.min");
                Integer espressoGrindMax = g.getInt("espressoGrind.max");
                Integer mokaPotGrindMin = g.getInt("mokaPotGrind.min");
                Integer mokaPotGrindMax = g.getInt("mokaPotGrind.max");
                Integer v60GrindMin = g.getInt("v60Grind.min");
                Integer v60GrindMax = g.getInt("v60Grind.max");
                Integer frenchPressGrindMin = g.getInt("frenchPressGrind.min");
                Integer frenchPressGrindMax = g.getInt("frenchPressGrind.max");

                Grinder grinder = new Grinder(
                        name, description,
                        Range.of(aeropressGrindMin,aeropressGrindMax),
                        Range.of(espressoGrindMin,espressoGrindMax),
                        Range.of(mokaPotGrindMin,mokaPotGrindMax),
                        Range.of(v60GrindMin,v60GrindMax),
                        Range.of(frenchPressGrindMin,frenchPressGrindMax)
                );

                allGrinders.add(grinder);
            }
            return allGrinders;

        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }
    }
}
