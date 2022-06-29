package com.example.betabit;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

public class SceneController {
    HashMap<String, URL> screens = new HashMap<>();

    public void addScreen(String name, String screen) {
        screens.put(name, HelloApplication.class.getResource(screen));
    }

    public Scene activate(String name) throws IOException {
        FXMLLoader loader = new FXMLLoader(screens.get(name));
        return new Scene(loader.load(), 800, 600);
    }

    public void loadScreen(String name, Stage window) {
        try {
            window.setScene(activate(name));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}