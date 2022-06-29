package com.example.betabit.container;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class ProfileContainer extends Container {
    public static ArrayList<ProfileContainer> profileContainers = new ArrayList<>();

    public ProfileContainer(int id, String name, Color color, int borderWidth, int pricePoints, int priceBadges) {
        super(id, name, color, borderWidth, pricePoints, priceBadges);
        profileContainers.add(this);
    }
}