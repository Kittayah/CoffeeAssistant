package org.fokkittah.coffeeassistant;

import org.fokkittah.coffeeassistant.panelsGUI.welcomeScreen.CardLayoutManager;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import javax.swing.*;

@SpringBootApplication
public class CoffeeAssistantApplication extends JFrame {

    public static void main(String[] args) {
        var ctx = new SpringApplicationBuilder(CoffeeAssistantApplication.class).headless(false).run(args);
        ctx.getBean(CardLayoutManager.class);

//        MainGui mainGui = new MainGui();
//        WelcomeScreenGui welcomeScreenGui = new WelcomeScreenGui();
//        mainGui.createAndShowGUI();

    }

}
