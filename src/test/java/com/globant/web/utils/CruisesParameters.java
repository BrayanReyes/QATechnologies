package com.globant.web.utils;

public class CruisesParameters {

    private String cruiseDestination;
    private int departAsEarly;
    private int departAsLate;

    public CruisesParameters(String destination, int departAsEarly, int departAsLate) {
        this.cruiseDestination = destination;
        this.departAsEarly = departAsEarly;
        this.departAsLate = departAsLate;
    }


    public String getCruiseDestination() {
        return cruiseDestination;
    }

    public int getDepartAsEarly() {
        return departAsEarly;
    }

    public int getDepartAsLate() {
        return departAsLate;
    }
}
