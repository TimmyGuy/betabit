package com.example.betabit;

import com.example.betabit.container.PictureContainer;
import com.example.betabit.container.ProfileContainer;
import com.example.betabit.store.Store;
import com.example.betabit.transport.Transport;
import com.example.betabit.transport.TravelPreference;
import com.example.betabit.user.User;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    public static User user;
    public static SceneController sceneController = new SceneController();
    public static Store store;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Betabit");
        try {
            stage.setScene(sceneController.activate("login"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        stage.show();
    }

    public static void loading() {
        // load the screens
        sceneController.addScreen("dashboard", "hello-view.fxml");
        sceneController.addScreen("login", "login-view.fxml");
        sceneController.addScreen("register", "register-view.fxml");

        // Transport list
        new Transport(1, "Benzine auto", 192);
        new Transport(2, "Diesel auto", 171);
        new Transport(3, "Bus (OV)", 89);
        new Transport(4, "Trein (OV)", 150);
        new Transport(5, "Fiets", 5);
        new Transport(6, "Lopen", 0);

        // PictureContainer list
        new PictureContainer(1, "Bild 1", Color.BLUE, 1, 0, 0);
        new PictureContainer(2, "Bild 2", Color.RED, 2, 500, 0);
        new PictureContainer(3, "Bild 3", Color.GREEN, 3, 750, 0);
        new PictureContainer(4, "Bild 4", Color.CORAL, 4, 300, 4);

        // ProfileContainer list
        new ProfileContainer(1, "Bild 1", Color.BLUE, 1, 0, 0);
        new ProfileContainer(2, "Bild 2", Color.RED, 2, 500, 0);
        new ProfileContainer(3, "Bild 3", Color.GREEN, 3, 750, 0);
        new ProfileContainer(4, "Bild 4", Color.CORAL, 4, 300, 4);

        // User list
        User.users.add(new User("Tim Ohlsen", "timohlsen", "test123", "Admin", 60, 60, 0, 0, PictureContainer.pictureContainers.get(3), ProfileContainer.profileContainers.get(2), new TravelPreference(-1, null)));
    }

    public static void main(String[] args) {
        loading();
        launch();
    }
}