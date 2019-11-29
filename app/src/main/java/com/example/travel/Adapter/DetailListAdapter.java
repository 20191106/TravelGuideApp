package com.example.travel.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.example.travel.Model.Place;
import com.example.travel.R;

import java.util.ArrayList;


public class DetailListAdapter extends ArrayAdapter {
    LayoutInflater lnf;
    ArrayList<Place> places;
    Activity context;

    public DetailListAdapter(Activity context, ArrayList<Place> places) {
        super(context, R.layout.row_detail_list, places);
        this.context = context;
        lnf = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.places = places;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return places.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return places.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        DetailListHolder viewHolder;

        if (convertView == null) {
            convertView = lnf.inflate(R.layout.row_detail_list, parent, false);
            viewHolder = new DetailListHolder();

            viewHolder.place = convertView.findViewById(R.id.detail_list_place);
            viewHolder.detail = convertView.findViewById(R.id.detail_list_detail);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (DetailListHolder) convertView.getTag();
        }

        viewHolder.place.setText(places.get(position).name);
        viewHolder.detail.setText(places.get(position).detail);

        return convertView;
    }
}
