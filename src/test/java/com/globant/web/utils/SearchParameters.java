package com.globant.web.utils;

public class SearchParameters {

    private String flyingFrom;
    private String flyingTo;
    private String numberOfAdults = "2";
    private String numberOfChildren = "0";
    private String[] childrenAge;
    private int flightDaysForward;
    private int flightDaysToReturn;
    private int checkInDaysForward;
    private int checkOutDaysToReturn;
    private String flightsSortCriteria;
    private int departureFlightOption;
    private int returnFlightOption;
    private String bookerFirstName;
    private String bookerLastName;

    /**
     * Constructor for perform a Flight Search
     */
    public SearchParameters(String flyingFrom, String flyingTo, String numberOfAdults, String numberOfChildren,
                            String[] childrenAge, int flightDaysForward, int flightDaysToReturn, String flightsSortCriteria,
                            int departureFlightOption, int returnFlightOption, String bookerFirstName, String bookerLastName){

        this.flyingFrom=flyingFrom;
        this.flyingTo=flyingTo;
        this.numberOfAdults=numberOfAdults;
        this.numberOfChildren=numberOfChildren;
        this.childrenAge=childrenAge;
        this.flightDaysForward = flightDaysForward;
        this.flightDaysToReturn = flightDaysToReturn;
        this.flightsSortCriteria = flightsSortCriteria;
        this.departureFlightOption=departureFlightOption;
        this.returnFlightOption=returnFlightOption;
        this.bookerFirstName = bookerFirstName;
        this.bookerLastName = bookerLastName;
}

    public String getFlyingFrom() {
        return flyingFrom;
    }

    public String getFlyingTo() {
        return flyingTo;
    }

    public String getNumberOfAdults() {
        return numberOfAdults;
    }

    public String getNumberOfChildren() {
        return numberOfChildren;
    }

    public String[] getChildrenAge() {
        return childrenAge;
    }

    public int getFlightDaysForward() {
        return flightDaysForward;
    }

    public int getFlightDaysToReturn() {
        return flightDaysToReturn;
    }

    public int getDepartureFlightOption() { return departureFlightOption; }

    public int getReturnFlightOption() { return returnFlightOption; }

    public String getFlightsSortCriteria() { return flightsSortCriteria; }

    public int getCheckInDaysForward() { return checkInDaysForward; }

    public int getCheckOutDaysToReturn() {
        return checkOutDaysToReturn;
    }

    public String getBookerFirstName() {
        return bookerFirstName;
    }

    public String getBookerLastName() {
        return bookerLastName;
    }
}
