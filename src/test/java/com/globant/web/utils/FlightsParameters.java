package com.globant.web.utils;

public class FlightsParameters {

    private String flyingFrom;
    private String flyingTo;
    private String numberOfAdults = "2";
    private String numberOfChildren = "0";
    private String[] childrenAge;
    private int daysForward;
    private int daysToReturn;
    private String duration;
    private int returnFlightOption;

    /**
     * Constructor
     */
    public FlightsParameters(String flyingFrom, String flyingTo, String numberOfAdults, String numberOfChildren,
                             String[] childrenAge, int daysForward, int daysToReturn, String duration, int returnFlightOption){
        this.flyingFrom=flyingFrom;
        this.flyingTo=flyingTo;
        this.numberOfAdults=numberOfAdults;
        this.numberOfChildren=numberOfChildren;
        this.childrenAge=childrenAge;
        this.daysForward=daysForward;
        this.daysToReturn=daysToReturn;
        this.duration=duration;
        this.returnFlightOption=returnFlightOption;

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

    public int getDaysForward() {
        return daysForward;
    }

    public int getDaysToReturn() {
        return daysToReturn;
    }

    private void setFlyingFrom(String flyingFrom) {
        this.flyingFrom = flyingFrom;
    }

    private void setFlyingTo(String flyingTo) {
        this.flyingTo = flyingTo;
    }

    private void setNumberOfAdults(String numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }

    private void setNumberOfChildren(String numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    private void setChildrenAge(String[] childrenAge) {
        this.childrenAge = childrenAge;
    }

    private void setDaysForward(int daysForward) {
        this.daysForward = daysForward;
    }

    private void setDaysToReturn(int daysToReturn) {
        this.daysToReturn = daysToReturn;
    }

    public int getReturnFlightOption() {
        return returnFlightOption;
    }

    public String getDuration() {
        return duration;



    }
}
