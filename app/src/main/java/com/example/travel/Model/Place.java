package com.example.travel.Model;

public class Place {
    String name;
    String[] imgAdress; // max3
    String detail;
    double latitude;
    double longitude;

    public Place(String name, String[] imgAdress, String detail, double latitude, double longitude) {
        this.name = name;
        this.imgAdress = imgAdress;
        this.detail = detail;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
