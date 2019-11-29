package com.example.travel.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.travel.FragImagePager;

public class ImagePageAdapter extends FragmentStatePagerAdapter {
    String[] img_address;

    public ImagePageAdapter(@NonNull FragmentManager fm, String[] img_address) {
        super(fm);
        this.img_address = img_address;
    }

    public ImagePageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return new FragImagePager(img_address[position]);
    }

    @Override
    public int getCount() {
        if(img_address[1].equals("")) return 1;
        else if(img_address[2].equals("")) return 2;
        else return 3;
    }
}
