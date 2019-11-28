package com.example.travel.Model;

import java.util.ArrayList;

// -
// Main Data
// ㄴ Attractions
//    ㄴ Places
//       ㄴ 3 images
//       ㄴ 1 text
// -

public class Attraction {
    public String category;
    public String local;
    public double star;
    public boolean isMarked;
    public ArrayList<Place> places;

    public Attraction(String category, String local, double star, boolean isMarked, ArrayList<Place> places) {
        this.category = category;
        this.local = local;
        this.star = star;
        this.isMarked = isMarked;
        this.places = places;
    }
}
