package com.example.betabit.container;

import com.example.betabit.store.Store;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.paint.Color;

public abstract class Container {
    private int id;
    private SimpleStringProperty name;
    private Color color;
    private int borderWidth;
    private SimpleIntegerProperty pricePoints;
    private SimpleIntegerProperty priceBadges;

    public Container(int id, String name, Color color, int borderWidth, int pricePoints, int priceBadges) {
        this.id = id;
        this.name = new SimpleStringProperty(name);
        this.color = color;
        this.borderWidth = borderWidth;
        this.pricePoints = new SimpleIntegerProperty(pricePoints);
        this.priceBadges = new SimpleIntegerProperty(priceBadges);

        // Add container to store
        Store store = Store.getInstance();
        store.addProduct(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
    }

    public int getPricePoints() {
        return pricePoints.get();
    }

    public void setPricePoints(int pricePoints) {
        this.pricePoints = new SimpleIntegerProperty(pricePoints);
    }

    public int getPriceBadges() {
        return priceBadges.get();
    }

    public void setPriceBadges(int priceBadges) {
        this.priceBadges = new SimpleIntegerProperty(priceBadges);
    }
}