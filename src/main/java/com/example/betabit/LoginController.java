package com.example.betabit;

import com.example.betabit.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static com.example.betabit.user.User.users;

public class LoginController {

    @FXML
    private Text incorrect_password;

    @FXML
    private Text incorrect_user;

    @FXML
    private Button login_btn;

    @FXML
    private PasswordField passsword_input;

    @FXML
    private TextField username_input;

    @FXML
    void loginUser(ActionEvent event) {
        boolean foundUser = false;
        incorrect_user.setFill(Color.TRANSPARENT);
        incorrect_password.setFill(Color.TRANSPARENT);

        for (User user : users) {
            if (user.getEmail().equals(username_input.getText())) {
                if (user.getPassword().equals(passsword_input.getText())) {
                    foundUser = true;
                    HelloApplication.user = user;

                    Stage stage = (Stage) login_btn.getScene().getWindow();
                    HelloApplication.sceneController.loadScreen("dashboard", stage);

                } else {
                    incorrect_password.setFill(Color.RED);
                }
            }
        }
        if (!foundUser) {
            incorrect_user.setFill(Color.RED);
        }
    }

}
