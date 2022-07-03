package com.example.betabit.store;

import com.example.betabit.HelloApplication;
import com.example.betabit.container.Container;
import com.example.betabit.container.PictureContainer;
import com.example.betabit.container.ProfileContainer;
import com.example.betabit.user.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.util.Observable;

public class Store extends Observable {
    private static final ObservableList<Container> products = FXCollections.observableArrayList();

    private static Store instance;

    // Make class singleton
    public static Store getInstance() {
        if(instance == null) {
            instance = new Store();
        }
        return instance;
    }

    public static ObservableList<Container> getProducts() {
        // Filter out Containers that are also in user container
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

    public boolean handleTransaction(User user, Container container) {
        Store store = getInstance();

        if(user.getContainers().contains(container)) {
            store.enable(container);
            return true;
        } else if(user.getCurrentPoints() >= container.getPricePoints() && user.getCurrentBadges() >= container.getPriceBadges()) {
            store.buy(container);
            user.setCurrentPoints(user.getCurrentPoints() - container.getPricePoints());
            user.setCurrentBadges(user.getCurrentBadges() - container.getPriceBadges());

            return true;
        }
        return false;
    }

    public void buy(Container product) {
        HelloApplication.user.addContainer(product);
        if(product instanceof ProfileContainer) {
            HelloApplication.user.setProfileContainer((ProfileContainer) product);
        } else if (product instanceof PictureContainer) {
            HelloApplication.user.setPictureContainer((PictureContainer) product);
        }
        setChanged();
        notifyObservers();
    }

    public void enable(Container product) {
        if(product instanceof ProfileContainer) {
            HelloApplication.user.setProfileContainer((ProfileContainer) product);
        } else if (product instanceof PictureContainer) {
            HelloApplication.user.setPictureContainer((PictureContainer) product);
        }
        setChanged();
        notifyObservers();
    }

}
