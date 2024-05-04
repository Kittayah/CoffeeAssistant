package org.fokkittah.coffeeassistant;

import org.fokkittah.coffeeassistant.panelsGUI.MainGui;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;

@SpringBootApplication
public class CoffeeAssistantApplication extends JFrame {

    public static void main(String[] args) {
        SpringApplication.run(CoffeeAssistantApplication.class, args);

        MainGui mainGui = new MainGui();
        mainGui.createAndShowGUI();

    }

}
