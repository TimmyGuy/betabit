package com.example.betabit;

import com.example.betabit.container.Container;
import com.example.betabit.store.Store;
import com.example.betabit.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class StoreController implements Initializable {

    private Container container;

    @FXML
    private Label badges;

    @FXML
    private Label name;

    @FXML
    private Label points;

    @FXML
    private Pane color;

    @FXML
    private Button buyButton;

    @FXML
    void buy(ActionEvent event) {
        User user = HelloApplication.user;
        Store store = Store.getInstance();

        if(user.getContainers().contains(container)) {
            store.enable(container);
        } else if(user.getCurrentPoints() >= container.getPricePoints() && user.getCurrentBadges() >= container.getPriceBadges()) {
            store.buy(container);
            user.setCurrentPoints(user.getCurrentPoints() - container.getPricePoints());
            user.setCurrentBadges(user.getCurrentBadges() - container.getPriceBadges());

            Stage stage = (Stage) name.getScene().getWindow();
            HelloApplication.sceneController.loadScreen("dashboard", stage);
        }
    }

    @FXML
    void returnToDashboard(ActionEvent event) {
        Stage stage = (Stage) name.getScene().getWindow();
        HelloApplication.sceneController.loadScreen("dashboard", stage);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        User user = HelloApplication.user;
        container = HelloApplication.container;
        badges.setText(container.getPriceBadges() + "");
        name.setText(container.getName());
        points.setText(container.getPricePoints() + "");
        color.setBackground(new Background(new BackgroundFill(container.getColor(), null, null)));

        if(user.getContainers().contains(container)) {
            buyButton.setText("Zet aan");
            buyButton.setDisable(false);
        } else if(user.getCurrentPoints() < container.getPricePoints() || user.getCurrentBadges() < container.getPriceBadges()) {
            buyButton.setText("Not enough points");
            buyButton.setDisable(true);
        } else {
            buyButton.setText("Buy");
            buyButton.setDisable(false);
        }
    }
}
