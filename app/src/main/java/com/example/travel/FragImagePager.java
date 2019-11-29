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

public class FragImagePager extends BaseFragment {
    String img_address;
    ImageView image;
    public FragImagePager(String img_address) {
        this.img_address = img_address;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.row_image_page, null, false);
        MainActivity m = ((MainActivity)getActivity());

        image = v.findViewById(R.id.image_page_image);
        Glide.with(m).load(img_address).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(image);

        return v;
    }
}
