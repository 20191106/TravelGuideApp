package com.example.travel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.travel.Model.Place;

public class FragMap extends BaseFragment {
    Place place;
    int position_att;

    public void setPlace(Place place){
        this.place = place;
    }

    public void setPosition_att(int position_att) {
        this.position_att = position_att;
    }

    ImageView ImageBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_map, container, false);
        final MainActivity m = ((MainActivity)getActivity());

        Glide.with(m).load(R.drawable.list_icon).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(ImageBtn);

        ImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m.popImage(place, position_att);
            }
        });

        return v;
    }
}
