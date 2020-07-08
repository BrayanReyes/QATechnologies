package com.globant.web.utils;

public class CruisesParameters {

    private String cruiseDestination;
    private int daysForward;
    private int stayDuration;

    public CruisesParameters(String destination, int daysForward, int stayDuration){
        this.cruiseDestination=destination;
        this.daysForward=daysForward;
        this.stayDuration=stayDuration;
    }


    public String getCruiseDestination() {
        return cruiseDestination;
    }

    public int getDaysForward() {
        return daysForward;
    }

    public int getStayDuration() {
        return stayDuration;
    }
}
