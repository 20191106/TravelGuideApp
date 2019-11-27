package com.example.travel.Model;

import java.util.ArrayList;

// -
// Main Data
// -

public class Attraction {
    String category;
    String local;
    int star;
    boolean isMarked;
    ArrayList<Place> places = new ArrayList<>();

    public Attraction(String category, String local, int star, boolean isMarked, ArrayList<Place> places) {
        this.category = category;
        this.local = local;
        this.star = star;
        this.isMarked = isMarked;
        this.places = places;
    }
}
