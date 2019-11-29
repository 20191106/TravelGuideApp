package com.example.travel.Adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.travel.Model.Attraction;
import com.example.travel.R;
import com.example.travel.Util;

import java.util.ArrayList;

public class MainAdapter extends ArrayAdapter {
    LayoutInflater lnf;
    ArrayList<Attraction> attractions;
    Activity context;

    public MainAdapter(Activity context, ArrayList<Attraction> attractions) {
        super(context, R.layout.row_main_att, attractions);
        this.context = context;
        lnf = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.attractions = attractions;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return attractions.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return attractions.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        MainHolder viewHolder;

        if (convertView == null) {
            convertView = lnf.inflate(R.layout.row_main_att, parent, false);
            viewHolder = new MainHolder();

            viewHolder.filterTv = convertView.findViewById(R.id.main_att_filterTv);
            viewHolder.placeTv = convertView.findViewById(R.id.main_att_placeTv);
            viewHolder.mark = convertView.findViewById(R.id.main_att_mark);
            viewHolder.star_1 = convertView.findViewById(R.id.main_att_star_1);
            viewHolder.star_2 = convertView.findViewById(R.id.main_att_star_2);
            viewHolder.star_3 = convertView.findViewById(R.id.main_att_star_3);
            viewHolder.star_4 = convertView.findViewById(R.id.main_att_star_4);
            viewHolder.star_5 = convertView.findViewById(R.id.main_att_star_5);
            viewHolder.images = convertView.findViewById(R.id.main_att_images);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (MainHolder) convertView.getTag();
        }

        viewHolder.filterTv.setText(attractions.get(position).category);
        viewHolder.placeTv.setText(attractions.get(position).local);
        if(attractions.get(position).isMarked) Glide.with(context).load(R.drawable.top_bookmark_on).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(viewHolder.mark);
        else Glide.with(context).load(R.drawable.top_bookmark_off).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(viewHolder.mark);
        viewHolder.star_1.setVisibility(View.GONE);
        viewHolder.star_2.setVisibility(View.GONE);
        viewHolder.star_3.setVisibility(View.GONE);
        viewHolder.star_4.setVisibility(View.GONE);
        viewHolder.star_5.setVisibility(View.GONE);
        if(attractions.get(position).star >= 1) viewHolder.star_1.setVisibility(View.VISIBLE);
        if(attractions.get(position).star >= 2) viewHolder.star_2.setVisibility(View.VISIBLE);
        if(attractions.get(position).star >= 3) viewHolder.star_3.setVisibility(View.VISIBLE);
        if(attractions.get(position).star >= 4) viewHolder.star_4.setVisibility(View.VISIBLE);
        if(attractions.get(position).star >= 5) viewHolder.star_5.setVisibility(View.VISIBLE);
        for (int i = 0; i < attractions.get(position).places.size() && i < 3; i++) {
            ImageView image = new ImageView(context);
            image.setPadding(0, Util.dpToPx(context, 10), 0, 0);
            Glide.with(context).load((attractions.get(position)).places.get(i).imgAdress[0]).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).override(10000, Util.dpToPx(context, 90)).into(image);
            Log.d("ah", "getView: " + i);
//            ViewGroup.LayoutParams params = image.getLayoutParams();
//            params.width = params.width * dpToPx(context, 90) / params.height;
//            params.height = dpToPx(context, 90);
//            image.setLayoutParams(params);
            viewHolder.images.addView(image);
        }

        return convertView;
    }


}
