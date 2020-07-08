package com.globant.web.utils;

public class HotelsParameters {

    private String destination;
    private int checkInForward;
    private int checkOutForward;
    private String hotelName;
    private String email;


    public HotelsParameters(String destination, int checkInForward, int checkOutForward, String hotelName, String email){
        this.destination=destination;
        this.checkInForward=checkInForward;
        this.checkOutForward=checkOutForward;
        this.hotelName=hotelName;
        this.email=email;

    }


    public String getDestination() {
        return destination;
    }

    public int getCheckInForward() {
        return checkInForward;
    }

    public int getCheckOutForward() {
        return checkOutForward;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getEmail() {
        return email;
    }
}
