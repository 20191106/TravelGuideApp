package com.example.travel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity {
    ArrayList<Attraction> attractions = new ArrayList<>();

    TextView menuTv;

    FragMain fragMain = new FragMain();
    FragDetail fragDetail = new FragDetail();
    FragImage fragImage = new FragImage();
    FragMap fragMap = new FragMap();

    int curScr = 0;
    final static int FRAG_MAIN = 0;
    final static int FRAG_DETAIL = 1;
    final static int FRAG_IMAGE = 2;
    final static int FRAG_MAP = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuTv = findViewById(R.id.menuTv);

        InitSQL();
        InitData();

        popMain();
    }

    public void popMain() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.contentLayout, fragMain);
        ft.commit();
        curScr = FRAG_MAIN;
        menuTv.setText("추천 여행지");
    }

    public void popDetail(Attraction attraction, int position_att){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        fragDetail.setAttraction(attraction);
        fragDetail.setPosition_att(position_att);
        ft.replace(R.id.contentLayout, fragDetail);
        ft.commit();
        curScr = FRAG_DETAIL;
        menuTv.setText(attraction.local);
    }

    public void popImage(Place place, int position_att) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        fragImage.setPlace(place);
        fragImage.setPosition_att(position_att);
        ft.replace(R.id.contentLayout, fragImage);
        ft.commit();
        curScr = FRAG_IMAGE;
        menuTv.setText(place.name);
    }

    public void popMap(Place place, int position_att) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        fragMap.setPlace(place);
        fragMap.setPosition_att(position_att);
        ft.replace(R.id.contentLayout, fragMap);
        ft.commit();
        curScr = FRAG_MAP;
        menuTv.setText(place.name);
    }

    @Override
    public void onBackPressed() {
        switch (curScr){
            case FRAG_MAIN:
                super.onBackPressed();
                break;
            case FRAG_DETAIL:
                popMain();
                break;
            case FRAG_IMAGE:
                popDetail(attractions.get(fragImage.position_att), fragImage.position_att);
                break;
            case FRAG_MAP:
                popDetail(attractions.get(fragMap.position_att), fragMap.position_att);
                break;
        }
    }




    //-------------------------------------
    //   GET DATA
    //-------------------------------------


    private void InitData() {
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
                                Log.d("ah", "data_list: "+data_list);
                                for (int i = 0; i < data_list.length(); i++) {
                                    JSONObject now_att = data_list.getJSONObject(i);
                                    JSONArray place_list = now_att.getJSONArray("place");
                                    ArrayList<Place> places = new ArrayList<>();
                                    Log.d("ah", "place_list: "+place_list);
                                    for (int j = 0; j < place_list.length(); j++) {
                                        JSONObject now_place = place_list.getJSONObject(j);
                                        places.add(new Place(now_place.getString("name"),
                                                            new String[]{now_place.getString("img_adress_1"),
                                                                    now_place.getString("img_adress_2"),
                                                                    now_place.getString("img_adress_3")},
                                                            now_place.getString("detail"),
                                                            now_place.getDouble("latitude"),
                                                            now_place.getDouble("longitude")));
                                    }
                                    int idx = Integer.parseInt(now_att.getString("idx"));
                                    attractions.add(
                                            new Attraction(idx,
                                                    now_att.getString("category"),
                                                    now_att.getString("local"),
                                                    now_att.getDouble("star"),
                                                    getMark(idx),
                                                    places));
                                    fragMain.mainAdapter.notifyDataSetChanged();
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

    private void InitSQL() {//init
        SQLiteDatabase db = openOrCreateDatabase("my_db.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS mydb("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "idx INTEGER, "
                + "mark INTEGER" + ");");
        db.close();
    }

    private boolean getMark(int idx){
        boolean mark = false;
        SQLiteDatabase db = openOrCreateDatabase("my_db.db", MODE_PRIVATE, null);
        Cursor c = db.rawQuery("SELECT * FROM mydb", null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            if(c.getInt(1) == idx){
                if(c.getInt(2) == 0) return mark = false;
                else mark = true;
            }
            c.moveToNext();
        }
        c.close();
        db.close();
        return mark;
    }
}
