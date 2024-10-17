//package com.d06k14.wheatherapp;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.ListView;
//import android.widget.Toast;
//
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonObjectRequest;
//import com.android.volley.toolbox.Volley;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Locale;
//
//public class NextDayActivity extends AppCompatActivity {
//    List<Weather> weatherList;
//    WeaherAdapter adapter;
//    ListView lvNextDay;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_next_day);
//        lvNextDay = findViewById(R.id.lvNextDay);
//        Intent intent = getIntent();
//        String city = intent.getStringExtra("city");
//        weatherList = new ArrayList<>();
//        adapter = new WeaherAdapter(NextDayActivity.this, R.layout.row_weather, weatherList);
//        lvNextDay.setAdapter(adapter);
//        getJsonNextDay(city);
//    }
//
//    private void getJsonNextDay( final String city) {
//        String url = String.format("https://api.openweathermap.org/data/2.5/forecast?q=%s&units=metric&appid=95d77396172d9fd29b59367e790887ac", city);
//        RequestQueue queue = Volley.newRequestQueue(this);
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//            @SuppressLint("SetTextI18n")
//            @Override
//            public void onResponse(JSONObject response) {
//                //thanh cong chay ham nay
//                try {
//                    JSONArray list = response.getJSONArray("list");
//                    for (int i = 0; i < list.length(); i++) {
//                        JSONObject item = list.getJSONObject(i);
//                        String sNgay = item.getString("dt");
//                        long lNgay = Long.parseLong(sNgay);
//                        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat(" EEEE, dd.MM");
//                        Date date = new Date(lNgay * 1000);
//                        String dt = dateFormat.format(date);
//                        JSONObject main = item.getJSONObject("main");
//                        String temp2 = main.getString("temp");
//                        JSONArray weather = item.getJSONArray("weather");
//                        JSONObject weatherItem = weather.getJSONObject(0);
//                        String description = weatherItem.getString("description");
//                        weatherList.add(new Weather(dt, description, temp2));
//                    }
//                    adapter.notifyDataSetChanged();
//                } catch (JSONException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                //that bai chay ham nay
//                Toast.makeText(NextDayActivity.this, "API error", Toast.LENGTH_SHORT).show();
//            }
//        });
//        queue.add(jsonObjectRequest);
//    }
//}