package com.example.betabit;

import com.example.betabit.container.Container;
import com.example.betabit.log.Log;
import com.example.betabit.store.Store;
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
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import static com.example.betabit.HelloApplication.store;

public class HelloController implements Initializable, Observer {

    private User user;

    @FXML
    private Tab adminTab;

    @FXML
    private Label badges;

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
    private Label points;

    @FXML
    private Label pointsLeft;

    @FXML
    private Label position;

    @FXML
    private Pane profileContainer;

    @FXML
    private Label role;

    @FXML
    private Label selectKm;

    @FXML
    private Label selectVehicle;

    @FXML
    private Label totalBadges;

    @FXML
    private Label totalPoints;

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
    private TableView<Container> storeTable;

    @FXML
    private TableColumn<Container, Integer> badgesColumn;

    @FXML
    private TableColumn<Container, Integer> productPointsColumn;

    @FXML
    private TableColumn<Container, String> productNameColumn;

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
                points.setText(String.valueOf(user.getCurrentPoints()));
                totalPoints.setText(String.valueOf(user.getTotalPoints()));
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
        // add observer
        store.addObserver(this);

        // Set labels
        user = HelloApplication.user;
        name.setText(user.getFullName());
        email.setText(user.getEmail());
        nameLeft.setText(user.getFullName());

        // Set points
        pointsLeft.setText(String.valueOf(user.getCurrentPoints()));
        badgesLeft.setText(String.valueOf(user.getCurrentBadges()));
        totalPoints.setText(String.valueOf(user.getTotalPoints()));
        totalBadges.setText(String.valueOf(user.getTotalBadges()));
        badges.setText(String.valueOf(user.getCurrentBadges()));
        points.setText(String.valueOf(user.getCurrentPoints()));
        role.setText(user.getRole());
        position.setText(String.valueOf(user.getPosition()));

        // make vehicle dropdown list from Transport.transports
        ObservableList<Transport> transportList = FXCollections.observableList(Transport.transports);
        vehicleDropdown.setItems(transportList);

        // Make admin tab visible if user is admin
        adminTab.setDisable(!user.getRole().equals("Admin"));

        // Set borders on pictureContainer
        setBorders();

        // Fill tables
        loadScorelist();
        loadProductTable();
    }

    private void loadProductTable() {
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productPointsColumn.setCellValueFactory(new PropertyValueFactory<>("pricePoints"));
        badgesColumn.setCellValueFactory(new PropertyValueFactory<>("priceBadges"));

        storeTable.setItems(Store.getProducts());
        storeTable.setRowFactory(tv -> {
            TableRow<Container> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                HelloApplication.container = row.getItem();
                Stage stage = (Stage) name.getScene().getWindow();
                HelloApplication.sceneController.loadScreen("store", stage);
            });
            return row;
        });
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

    @FXML
    void logout(ActionEvent event) {
        HelloApplication.user = null;
        HelloApplication.sceneController.loadScreen("login", (Stage) container.getScene().getWindow());
    }

    @Override
    public void update(Observable o, Object arg) {
        storeTable.refresh();
    }
}
