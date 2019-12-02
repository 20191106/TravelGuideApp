package com.example.travel;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.travel.Adapter.ImagePageAdapter;
import com.example.travel.Model.Place;

public class FragImage extends BaseFragment {
    Place place;
    int position_att;

    public void setPlace(Place place){
        this.place = place;
    }

    public void setPosition_att(int position_att) {
        this.position_att = position_att;
    }

    ViewPager pager;
    TextView text;
    ImageView mapBtn;

    ImagePageAdapter ipAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_image, container, false);
        final MainActivity m = ((MainActivity)getActivity());

        pager = v.findViewById(R.id.image_pager);
        text = v.findViewById(R.id.image_text);
        mapBtn = v.findViewById(R.id.image_mapBtn);

        text.setText(place.detail);
        Glide.with(m).load(R.drawable.map_icon).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(mapBtn);

        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m.popMap(place, position_att);
            }
        });

        ipAdapter = new ImagePageAdapter(getFragmentManager(), place.imgAdress);
        pager.setAdapter(ipAdapter);

        return v;
    }
}
