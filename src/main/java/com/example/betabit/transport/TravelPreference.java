package com.example.betabit.transport;

import java.util.ArrayList;

public class TravelPreference {
    static ArrayList<TravelPreference> travelPreferences = new ArrayList<TravelPreference>();

    private int distance;
    private Transport transport;

    public TravelPreference(int distance, Transport transport) {
        this.distance = distance;
        this.transport = transport;
        travelPreferences.add(this);
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }
}