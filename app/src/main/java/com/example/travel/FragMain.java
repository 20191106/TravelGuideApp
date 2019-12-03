package com.example.travel;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.travel.Adapter.MainAdapter;

public class FragMain extends BaseFragment {
    ListView main_attLv;
    MainAdapter mainAdapter;

    boolean lastitemVisibleFlag = false;
    int count = 40;
    boolean isLoadingEnd = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_main, container, false);
        final MainActivity m = ((MainActivity)getActivity());

        main_attLv = v.findViewById(R.id.main_attLv);

        mainAdapter = new MainAdapter(m, m.attractions);
        main_attLv.setAdapter(mainAdapter);

        main_attLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                m.popDetail(m.attractions.get(position), position);
            }
        });

        isLoadingEnd = m.isLoadingEnd;

        main_attLv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && lastitemVisibleFlag){
                    if(!isLoadingEnd){
                        isLoadingEnd = m.loadData(count);
                        count += 20;
                    }
                    mainAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                lastitemVisibleFlag = (totalItemCount > 0) && (firstVisibleItem + visibleItemCount >= totalItemCount);
            }
        });

        return v;
    }
}
