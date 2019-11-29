package com.example.travel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.travel.Model.Place;

import java.util.ArrayList;

public class FragDetailPager extends BaseFragment{
    ImageView image;
    TextView text;

    Place place;

    public FragDetailPager(Place place) {
        this.place = place;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.row_detail_page, null, false);
        MainActivity m = ((MainActivity)getActivity());

        image = v.findViewById(R.id.detail_page_image);
        text = v.findViewById(R.id.detail_page_text);

        Glide.with(m).load(place.imgAdress[0]).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(image);
        text.setText(place.detail);
        
        return v;
    }
}
