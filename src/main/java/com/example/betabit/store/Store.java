package com.example.betabit.store;

import com.example.betabit.container.Container;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Observable;

public class Store extends Observable {
    private final ObservableList<Container> products = FXCollections.observableArrayList();

    public ObservableList getProducts() {
        return products;
    }

    public void addProduct(Container product) {
        products.add(product);
        setChanged();
        notifyObservers();
    }

    public void removeProduct(Container product) {
        products.remove(product);
        setChanged();
        notifyObservers();
    }

}
