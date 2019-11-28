package com.example.travel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.travel.Model.Attraction;
import com.example.travel.Model.Place;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ArrayList<Attraction> attractions = new ArrayList<>();

    DrawerLayout mainLayout;
    TextView menuTv;

    FragMain fragMain = new FragMain();
    FragImage fragImage = new FragImage();
    FragDetail fragDetail = new FragDetail();
    FragMap fragMap = new FragMap();

    int curScr = 0;
    final static int FRAG_MAIN = 0;
    final static int FRAG_IMAGE = 1;
    final static int FRAG_DETAIL = 2;
    final static int FRAG_MAP = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = findViewById(R.id.mainLayout);
        mainLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        menuTv.findViewById(R.id.menuTv);

        getSeverData();
        getSqlData();

        popMain();
    }

    public void popMain() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.mainLayout, fragMain);
        ft.commit();
        curScr = FRAG_MAIN;
        menuTv.setText("추천 여행지");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    //-------------------------------------
    //   GET DATA
    //-------------------------------------


    private void getSeverData() {
        // (init) get all data except bookmark
        RequestQueue stringRequest = Volley.newRequestQueue(this);
        String temp = "http://jeho.dothome.co.kr/myDir/travel/get_att.php";
        StringRequest myReq = new StringRequest(Request.Method.GET,
                temp,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject data = new JSONObject(response);
                            if (data.getString("result").equals("OK")) {
                                JSONArray data_list = data.getJSONArray("data_list");
                                for (int i = 0; i < data_list.length(); i++) {
                                    JSONObject now_att = data_list.getJSONObject(i);
                                    final JSONObject now_place = now_att.getJSONObject("place");
                                    String idx = now_att.getString("idx");
//                                    attractions.add(
//                                            new Attraction(now_att.getString("category"),
//                                                    now_att.getString("local"),
//                                                    now_att.getDouble("star"),
//                                                    false,
//                                                    new Place(now_place.getString("name"),
//                                                            new String[]{now_place.getString("img_adress_1"),
//                                                                    now_place.getString("img_adress_2"),
//                                                                    now_place.getString("img_adress_3")},
//                                                            now_place.getString("detail"),
//                                                            now_place.getDouble("latitude"),
//                                                            now_place.getDouble("longitude"))));
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Error in MainActivity.getServerData()", Toast.LENGTH_LONG).show();
                    }
                });
        stringRequest.add(myReq);
    }

    private void getSqlData() {
        // (init) get only bookMark data
    }
}
