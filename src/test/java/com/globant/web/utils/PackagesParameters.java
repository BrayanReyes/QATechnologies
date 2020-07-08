package com.globant.web.utils;

public class PackagesParameters {

    private String flyingFrom;
    private String flyingTo;
    private int flightDaysForward;
    private int flightDaysToReturn;
    private int checkInDaysForward;
    private int checkOutDaysToReturn;
    private String adultsNumber = "2";
    private String firstName;
    private String lastName;

    /**
     * Constructor
     */
    public PackagesParameters(String flyingFrom, String flyingTo, int flightDaysForward,
                              int flightDaysToReturn, int checkInDaysForward, int checkOutDaysToReturn){
        this.flyingFrom=flyingFrom;
        this.flyingTo=flyingTo;
        this.flightDaysForward=flightDaysForward;
        this.flightDaysToReturn=flightDaysToReturn;
        this.checkInDaysForward=checkInDaysForward;
        this.checkOutDaysToReturn=checkOutDaysToReturn;
    }

    /**
     * Constructor to perform Flight, Hotel and Car Search Test
     */
    public PackagesParameters(String flyingFrom, String flyingTo, int flightDaysForward,
                              int flightDaysToReturn, String adultsNumber, String firstName, String lastName){
        this.flyingFrom=flyingFrom;
        this.flyingTo=flyingTo;
        this.flightDaysForward=flightDaysForward;
        this.flightDaysToReturn=flightDaysToReturn;
        this.adultsNumber=adultsNumber;
        this.firstName=firstName;
        this.lastName=lastName;

    }


    public String getFlyingFrom() {
        return flyingFrom;
    }

    public String getFlyingTo() {
        return flyingTo;
    }

    public int getFlightDaysForward() {
        return flightDaysForward;
    }

    public int getFlightDaysToReturn() {
        return flightDaysToReturn;
    }

    public int getCheckInDaysForward() {
        return checkInDaysForward;
    }

    public int getCheckOutDaysToReturn() {
        return checkOutDaysToReturn;
    }

    public String getAdultsNumber() { return adultsNumber; }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}

