package com.example.travel;


import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.travel.Adapter.DetailListAdapter;
import com.example.travel.Adapter.DetailPageAdapter;
import com.example.travel.Model.Attraction;

public class FragDetail extends BaseFragment {
    TextView filterTv;
    TextView placeTv;
    ImageView mark;
    ImageView star_1;
    ImageView star_2;
    ImageView star_3;
    ImageView star_4;
    ImageView star_5;
    ImageView showListBtn;
    ImageView showViewBtn;
    ViewPager pager;
    ListView listView;

    DetailPageAdapter dpAdapter;
    DetailListAdapter dlAdapter;

    Attraction attraction;

    int position_att;

    public void setAttraction(Attraction attraction) {
        this.attraction = attraction;
    }

    public void setPosition_att(int position_att) {
        this.position_att = position_att;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_detail, container, false);
        final MainActivity m = ((MainActivity)getActivity());

        filterTv = v.findViewById(R.id.detail_filterTv);
        placeTv = v.findViewById(R.id.detail_placeTv);
        mark = v.findViewById(R.id.detail_mark);
        star_1 = v.findViewById(R.id.detail_star_1);
        star_2 = v.findViewById(R.id.detail_star_2);
        star_3 = v.findViewById(R.id.detail_star_3);
        star_4 = v.findViewById(R.id.detail_star_4);
        star_5 = v.findViewById(R.id.detail_star_5);
        showListBtn = v.findViewById(R.id.detail_showListBtn);
        showViewBtn = v.findViewById(R.id.detail_showViewBtn);
        pager = v.findViewById(R.id.detail_pager);
        listView = v.findViewById(R.id.detail_listView);

        filterTv.setText(attraction.category);
        placeTv.setText(attraction.local);
        if(attraction.isMarked) Glide.with(m).load(R.drawable.top_bookmark_on).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(mark);
        else Glide.with(m).load(R.drawable.top_bookmark_off).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(mark);
        star_1.setVisibility(View.GONE);
        star_2.setVisibility(View.GONE);
        star_3.setVisibility(View.GONE);
        star_4.setVisibility(View.GONE);
        star_5.setVisibility(View.GONE);
        if(attraction.star >= 1) star_1.setVisibility(View.VISIBLE);
        if(attraction.star >= 2) star_2.setVisibility(View.VISIBLE);
        if(attraction.star >= 3) star_3.setVisibility(View.VISIBLE);
        if(attraction.star >= 4) star_4.setVisibility(View.VISIBLE);
        if(attraction.star >= 5) star_5.setVisibility(View.VISIBLE);

        Glide.with(m).load(R.drawable.top_list).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).override(1000, Util.dpToPx(m, 50)).into(showListBtn);
        Glide.with(m).load(R.drawable.top_view).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).override(1000, Util.dpToPx(m, 50)).into(showViewBtn);

        showListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
            }
        });
        showViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
            }
        });

        dpAdapter = new DetailPageAdapter(m.getSupportFragmentManager(), attraction.places);
        pager.setAdapter(dpAdapter);
        pager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m.popImage(attraction.places.get(pager.getCurrentItem()), position_att);
            }
        });

        dlAdapter = new DetailListAdapter(m, attraction.places);
        listView.setAdapter(dlAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                m.popImage(attraction.places.get(position), position_att);
            }
        });

        return v;
    }
}
