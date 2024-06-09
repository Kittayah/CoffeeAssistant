package org.fokkittah.coffeeassistant;

import org.fokkittah.coffeeassistant.gui.layoutmanager.CardLayoutManager;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import javax.swing.*;

/**
 * The main entry point for the Coffee Assistant application.
 * This class initializes the Spring Boot application and sets up the GUI.
 */
@SpringBootApplication
public class CoffeeAssistantApplication extends JFrame {

    /**
     * The main method to launch the Coffee Assistant application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Initialize the Spring Boot application with headless mode disabled
        var ctx = new SpringApplicationBuilder(CoffeeAssistantApplication.class).headless(false).run(args);

        // Get the CardLayoutManager bean to initialize the GUI
        ctx.getBean(CardLayoutManager.class);
    }
}
