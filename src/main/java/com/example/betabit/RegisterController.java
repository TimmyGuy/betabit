package com.example.betabit;

import com.example.betabit.user.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    private TextField email;

    @FXML
    private TextField fullName;

    @FXML
    private TextField password;

    @FXML
    private ComboBox<String> role;

    @FXML
    void addUser(ActionEvent event) {
        User user = new User(
                fullName.getText(),
                email.getText(),
                password.getText(),
                role.getValue(),
                0,
                0,
                0,
                0);
        User.users.add(user);
        Stage stage = (Stage) fullName.getScene().getWindow();
        HelloApplication.sceneController.loadScreen("dashboard", stage);
    }

    @FXML
    void returnToDashboard(ActionEvent event) {
        Stage stage = (Stage) fullName.getScene().getWindow();
        HelloApplication.sceneController.loadScreen("dashboard", stage);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> roles = FXCollections.observableArrayList("Admin", "Gebruiker");
        role.setItems(roles);
    }
}
