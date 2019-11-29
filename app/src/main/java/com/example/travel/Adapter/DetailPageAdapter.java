package com.example.travel.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.travel.FragDetailPager;
import com.example.travel.Model.Place;

import java.util.ArrayList;


public class DetailPageAdapter extends FragmentStatePagerAdapter {
    int position_att;
    ArrayList<Place> places;

    public DetailPageAdapter(@NonNull FragmentManager fm, ArrayList<Place> places) {
        super(fm);
        this.places = places;
    }

    public DetailPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return new FragDetailPager(places.get(position));
    }

    @Override
    public int getCount() {
        return places.size();
    }
}
