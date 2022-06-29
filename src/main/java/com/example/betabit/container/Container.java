package com.example.betabit.container;

import javafx.scene.paint.Color;

public abstract class Container {
    private int id;
    private String name;
    private Color color;
    private int borderWidth;
    private int pricePoints;
    private int priceBadges;

    public Container(int id, String name, Color color, int borderWidth, int pricePoints, int priceBadges) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.borderWidth = borderWidth;
        this.pricePoints = pricePoints;
        this.priceBadges = priceBadges;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return pricePoints;
    }

    public void setPricePoints(int pricePoints) {
        this.pricePoints = pricePoints;
    }

    public int getPriceBadges() {
        return priceBadges;
    }

    public void setPriceBadges(int priceBadges) {
        this.priceBadges = priceBadges;
    }
}