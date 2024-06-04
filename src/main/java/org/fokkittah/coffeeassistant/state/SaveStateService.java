package org.fokkittah.coffeeassistant.state;

import java.io.*;

public class SaveStateService {

    private Object currentState; //TODO replace Object with actual CoffeeAssistant state implementation

    public SaveStateService() throws IOException {
        currentState = new Object();
        loadState();
    }

    public Object getCurrentState() {
        return currentState;
    }


    public void loadState() throws IOException {
        String stateFileName = getUserDirectory() + "/coffeeAssistant/" + getUserName() + ".coffeebag";
        try (FileInputStream fileIn = new FileInputStream(stateFileName);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            currentState = in.readObject();
        } catch (ClassNotFoundException e) {
            System.err.println("Coffee assistant class not found");
            saveState();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveState() throws IOException {
        String stateFileName = getUserDirectory() + "/coffeeAssistant/" + getUserName() + ".coffeebag";
        try (FileOutputStream fileOut = new FileOutputStream(stateFileName);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(currentState);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getUserDirectory() {
        return System.getProperty("user.dir");
    }

    private String getUserName() {
        return System.getProperty("user.name");
    }
}
