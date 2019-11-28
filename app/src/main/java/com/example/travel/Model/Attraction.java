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
    public int idx;
    public String category;
    public String local;
    public double star;
    public boolean isMarked;
    public ArrayList<Place> places;

    public Attraction(int idx, String category, String local, double star, boolean isMarked, ArrayList<Place> places) {
        this.idx = idx;
        this.category = category;
        this.local = local;
        this.star = star;
        this.isMarked = isMarked;
        this.places = places;
    }
}
