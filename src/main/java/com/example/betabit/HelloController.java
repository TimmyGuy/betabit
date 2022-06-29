package com.example.betabit;

import com.example.betabit.log.Log;
import com.example.betabit.transport.Transport;
import com.example.betabit.user.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    private User user;

    @FXML
    private Label badgesLeft;

    @FXML
    private Pane container;

    @FXML
    private Label email;

    @FXML
    private TextField kmCount;

    @FXML
    private Label name;

    @FXML
    private Label nameLeft;

    @FXML
    private Label pointsLeft;

    @FXML
    private Pane profileContainer;

    @FXML
    private TableView<User> scorelist;

    @FXML
    private TableColumn<User, String> nameColumn;

    @FXML
    private TableColumn<User, Integer> pointsColumn;

    @FXML
    private TableColumn<User, Integer> positionColumn;

    @FXML
    private ComboBox<Transport> vehicleDropdown;

    @FXML
    private Tab adminTab;

    @FXML
    private Label selectKm;

    @FXML
    private Label selectVehicle;

    @FXML
    void onLogButtonClick(ActionEvent event) {
        selectKm.setTextFill(Color.TRANSPARENT);
        selectVehicle.setTextFill(Color.TRANSPARENT);

        if (vehicleDropdown.getValue() != null) {
            Transport transport = vehicleDropdown.getValue();

            if (!kmCount.getText().equals("")) {
                int km = Integer.parseInt(kmCount.getText());

                Log log = new Log(Log.logs.size() + 1, user, km, transport);
                Log.logs.add(log);

                user.setCurrentPoints((user.getCurrentPoints() + log.calculatePoints()));
                user.setTotalPoints((user.getTotalPoints() + log.calculatePoints()));

                pointsLeft.setText(String.valueOf(user.getCurrentPoints()));
                scorelist.refresh();
            } else {
                selectKm.setTextFill(Color.RED);
            }
        } else {
            selectVehicle.setTextFill(Color.RED);
        }
    }

    @FXML
    void addUsers(ActionEvent event) {
        Stage stage = (Stage) container.getScene().getWindow();
        HelloApplication.sceneController.loadScreen("register", stage);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user = HelloApplication.user;
        name.setText(user.getFullName());
        email.setText(user.getEmail());
        nameLeft.setText(user.getFullName());
        pointsLeft.setText(String.valueOf(user.getCurrentPoints()));
        badgesLeft.setText(String.valueOf(user.getCurrentBadges()));

        // make vehicle dropdown list from Transport.transports
        ObservableList<Transport> transportList = FXCollections.observableList(Transport.transports);
        vehicleDropdown.setItems(transportList);

        // Make admin tab visible if user is admin
        adminTab.setDisable(!user.getRole().equals("Admin"));

        // Set borders on pictureContainer
        setBorders();

        // Fill tables
        loadScorelist();
    }

    private void loadScorelist() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("totalPoints"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));

        scorelist.setItems(User.users);
    }

    private void setBorders() {
        profileContainer.setBorder(
                new Border(
                        new BorderStroke(
                                user.getPictureContainer().getColor(),
                                BorderStrokeStyle.SOLID,
                                CornerRadii.EMPTY,
                                new BorderWidths(
                                        user.getPictureContainer().getBorderWidth()
                                )
                        )
                )
        );
        container.setBorder(
                new Border(
                        new BorderStroke(
                                user.getProfileContainer().getColor(),
                                BorderStrokeStyle.SOLID,
                                CornerRadii.EMPTY,
                                new BorderWidths(
                                        user.getProfileContainer().getBorderWidth()
                                )
                        )
                )
        );
    }


}
