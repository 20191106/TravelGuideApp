package com.example.travel.Model;

public class Place {
    public String name;
    public String[] imgAdress; // max3
    public String detail;
    public double latitude;
    public double longitude;

    public Place(String name, String[] imgAdress, String detail, double latitude, double longitude) {
        this.name = name;
        this.imgAdress = imgAdress;
        this.detail = detail;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
