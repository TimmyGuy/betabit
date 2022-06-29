package com.example.betabit;

import com.example.betabit.user.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
    private Label nameLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label roleLabel;

    @FXML
    void addUser(ActionEvent event) {
        // clear labels
        nameLabel.setText("");
        emailLabel.setText("");

        if(validateEmail(email.getText())) {
            if(!userExists(email.getText())) {
                if(validateName(fullName.getText())) {
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
                } else {
                    nameLabel.setText("Voor en achternaam");
                }
            } else {
                emailLabel.setText("User already exists");
            }
        } else {
            emailLabel.setText("Geen geldig emailadres");
        }
    }

    // Regex for email validation
    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9_+&*-]+(?:\\."+
                    "[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                    "A-Z]{2,7}$";

    // Check email validation
    private boolean validateEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }

    // Check if user already exists
    private boolean userExists(String email) {
        for(User user : User.users) {
            if(user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    // Check if name has first and last name
    private boolean validateName(String name) {
        String[] names = name.split(" ");
        return names.length > 1;
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
