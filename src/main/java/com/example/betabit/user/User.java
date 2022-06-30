package com.example.betabit.user;

import com.example.betabit.container.Container;
import com.example.betabit.container.PictureContainer;
import com.example.betabit.container.ProfileContainer;
import com.example.betabit.transport.TravelPreference;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class User {
    public static ObservableList<User> users = FXCollections.observableArrayList();
    private SimpleStringProperty fullName;
    private String email;
    private String password;
    private String role;
    private int totalPoints;
    private SimpleIntegerProperty currentPoints;
    private int totalBadges;
    private int currentBadges;
    private PictureContainer pictureContainer;
    private ProfileContainer profileContainer;
    private TravelPreference travelPreference;
    private ArrayList<Container> containers = new ArrayList<>();

    public User() {
    }

    public User(String fullName, String email, String password, String role, int totalPoints, int currentPoints, int totalBadges, int currentBadges) {
        this(
                fullName,
                email,
                password,
                role,
                totalPoints,
                currentPoints,
                totalBadges,
                currentBadges,
                PictureContainer.pictureContainers.get(0),
                ProfileContainer.profileContainers.get(0),
                new TravelPreference(-1, null));
    }

    public User(
            String fullName,
            String email,
            String password,
            String role,
            int totalPoints,
            int currentPoints,
            int totalBadges,
            int currentBadges,
            PictureContainer pictureContainer,
            ProfileContainer profileContainer,
            TravelPreference travelPreference) {
        this.fullName = new SimpleStringProperty(fullName);
        this.email = email;
        this.password = password;
        this.role = role;
        this.totalPoints = totalPoints;
        this.currentPoints = new SimpleIntegerProperty(currentPoints);
        this.totalBadges = totalBadges;
        this.currentBadges = currentBadges;
        this.pictureContainer = pictureContainer;
        this.profileContainer = profileContainer;
        this.travelPreference = travelPreference;
        this.containers.add(profileContainer);
        this.containers.add(pictureContainer);
    }

    public String getFullName() {
        return fullName.get();
    }

    public void setFullName(String fullName) {
        this.fullName = new SimpleStringProperty(fullName);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getCurrentPoints() {
        return currentPoints.get();
    }

    public void setCurrentPoints(int currentPoints) {
        this.currentPoints = new SimpleIntegerProperty(currentPoints);
    }

    public int getTotalBadges() {
        return totalBadges;
    }

    public void setTotalBadges(int totalBadges) {
        this.totalBadges = totalBadges;
    }

    public int getCurrentBadges() {
        return currentBadges;
    }

    public void setCurrentBadges(int currentBadges) {
        this.currentBadges = currentBadges;
    }

    public PictureContainer getPictureContainer() {
        return pictureContainer;
    }

    public void setPictureContainer(PictureContainer pictureContainer) {
        this.pictureContainer = pictureContainer;
    }

    public ProfileContainer getProfileContainer() {
        return profileContainer;
    }

    public void setProfileContainer(ProfileContainer profileContainer) {
        this.profileContainer = profileContainer;
    }

    public TravelPreference getTravelPreference() {
        return travelPreference;
    }

    public void setTravelPreference(TravelPreference travelPreference) {
        this.travelPreference = travelPreference;
    }

    // Get position in list of users based on points
    public int getPosition() {
        int position = 1;
        for (User user : users) {
            if (user.getCurrentPoints() > this.getCurrentPoints()) {
                position++;
            }
        }
        return position;
    }

    public ArrayList<Container> getContainers() {
        return containers;
    }

    public void addContainer(Container container) {
        containers.add(container);
    }

    public void setContainers(ArrayList<Container> containers) {
        this.containers = containers;
    }
}
