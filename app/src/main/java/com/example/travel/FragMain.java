package com.example.travel;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.travel.Adapter.MainAdapter;

public class FragMain extends BaseFragment {
    ListView main_attLv;
    MainAdapter mainAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_main, container, false);
        final MainActivity m = ((MainActivity)getActivity());

        main_attLv = v.findViewById(R.id.main_attLv);

        mainAdapter = new MainAdapter(m, m.attractions);
        main_attLv.setAdapter(mainAdapter);

        mainAdapter.notifyDataSetChanged();
        return v;
    }
}
