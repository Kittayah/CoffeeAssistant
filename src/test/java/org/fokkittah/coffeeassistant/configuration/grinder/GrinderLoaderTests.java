package org.fokkittah.coffeeassistant.configuration.grinder;

import org.junit.jupiter.api.Test;

import java.util.List;

public class GrinderLoaderTests {
   GrinderLoader grinderLoader = new GrinderLoader();

    @Test
    public void printAllGrinders(){

        List<Grinder> grinders = grinderLoader.loadAllGrinders("src/test/resources/grinder.xml");
        System.out.println(grinders);
    }
}
