package com.example.betabit.container;

import javafx.scene.paint.Color;

import java.util.ArrayList;

import static com.example.betabit.HelloApplication.store;

public class PictureContainer extends Container {
    public static ArrayList<PictureContainer> pictureContainers = new ArrayList<>();

    public PictureContainer(int id, String name, Color color, int borderWidth, int pricePoints, int priceBadges) {
        super(id, name, color, borderWidth, pricePoints, priceBadges);
        pictureContainers.add(this);
        store.addProduct(this);
    }
}