package com.d06k14.wheatherapp;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.L;
import com.airbnb.lottie.Lottie;
import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;



import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private EditText searchView;
    private TextView tvTemp;
    private TextView tvMaxTemp;
    private TextView tvMinTemp;
    private TextView tvLocation;
    private TextView tvDay;
    private TextView tvDate;
    private TextView tvHumidity;
    private TextView tvWindSpeed;
    private TextView tvSea;
    private TextView tvSunrise;
    private TextView tvSunset;
    private TextView tvCondition;
    private TextView tvWeather;
    private ConstraintLayout CstrBackground;
    private LottieAnimationView lottieAnimationView2;

    private Button BtnOk;
    String city = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchView = findViewById((R.id.search_view));
        tvTemp = findViewById(R.id.tv_temp);
        tvMaxTemp = findViewById(R.id.tv_maxTemp);
        tvMinTemp = findViewById(R.id.tv_minTemp);
        tvLocation = findViewById(R.id.tv_location);
        tvDay = findViewById(R.id.tv_day);
        tvDate = findViewById(R.id.tv_date);
        tvHumidity = findViewById(R.id.tv_humidity);
        tvWindSpeed = findViewById(R.id.tv_windSpeed);
        tvSea = findViewById(R.id.tv_sea);
        tvSunrise = findViewById(R.id.tv_sunrise);
        tvSunset = findViewById(R.id.tv_sunset);
        tvCondition = findViewById(R.id.tv_condition);
        tvWeather = findViewById(R.id.tv_weather);
        CstrBackground = findViewById(R.id.cstr_background);
        lottieAnimationView2 = findViewById(R.id.lottieAnimationView2);
        BtnOk = findViewById(R.id.button);
        if (Objects.equals(city, "")) {
            searchWeatherByCityName("Hà Nội");
        } else searchWeatherByCityName(city);

    }
    public void defaultCity(View view) {
         city = searchView.getText().toString();
        // kiem tra du lieu
        if (city.isEmpty()) {
            searchView.setError("Please Enter The City Name");
            return;
        }
        searchWeatherByCityName(city);
    }
    public void searchWeatherByCityName(final String city) {
        //DA CO DU LIEU -> tao url phu hop
        final String url = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=70d4806e34bb6af3022c08ef7ba9f3ef&units=metric", city);
        //buoc 2: tao request len tren openweather
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(JSONObject response) {
                //thanh cong chay ham nay
                try {
                    //nhiệt độ
                    int temp = (int) response.getJSONObject("main").getDouble("temp");
                    tvTemp.setText( temp + "°C");
                    //độ ẩm
                    double humidity = response.getJSONObject("main").getDouble("humidity");
                    tvHumidity.setText(humidity + "%");
                    //tốc độ gió
                    double windSpeed = response.getJSONObject("wind").getDouble("speed");
                    tvWindSpeed.setText(windSpeed +"m/s");
                    //mực nước biển
                    if (response.getJSONObject("main").has("sea_level")) {
                        int seaLevel = (int) response.getJSONObject("main").getDouble("sea_level");
                        tvSea.setText(seaLevel +"hPa");
                    } else {
                        tvSea.setText("N/A");
                    }
//                    int seaLevel = (int) response.getJSONObject("main").getDouble("sea_level");
//                    tvSea.setText(seaLevel +"hPa");
                    //nhiệt dộ cao nhất
                    int temp_max = (int) response.getJSONObject("main").getDouble("temp_max");
                    tvMaxTemp.setText( "Max: " + temp_max + "°C");
                    //nhiệt độ thấp nhất
                    int temp_min = (int) response.getJSONObject("main").getDouble("temp_min");
                    tvMinTemp.setText("Min: " + temp_min + "°C");
                    // vị trị tìm kiếm
                    String country = response.getJSONObject("sys").getString("country");

                    String location = response.getString("name");
                    tvLocation.setText(location +" , " + country);
                    String condition = response.getJSONArray("weather").getJSONObject(0).getString("main");
                    tvWeather.setText(condition);
                    tvCondition.setText(condition);
                    if(condition.toLowerCase().equals("clouds") || condition.toLowerCase().equals("partly clouds") || condition.toLowerCase().equals("overcast") ||
                        condition.toLowerCase().equals("mist") || condition.toLowerCase().equals("froggy") || condition.toLowerCase().equals("haze") ||
                        condition.toLowerCase().equals("smoke") || condition.toLowerCase().equals("fog")){
                        CstrBackground.setBackgroundResource(R.drawable.colud_background);
                        lottieAnimationView2.setAnimation(R.raw.cloud);
                        lottieAnimationView2.playAnimation();
                    } else if(condition.toLowerCase().equals("clear") || condition.toLowerCase().equals("sunny") || condition.toLowerCase().equals("clear sky")){
                        CstrBackground.setBackgroundResource(R.drawable.sunny_background);
                        lottieAnimationView2.setAnimation(R.raw.sun);
                        lottieAnimationView2.playAnimation();
                    } else if(condition.toLowerCase().equals("rain") || condition.toLowerCase().equals("light rain") || condition.toLowerCase().equals("drizzle") ||
                            condition.toLowerCase().equals("moderate rain") || condition.toLowerCase().equals("showers") || condition.toLowerCase().equals("heavy rain")){
                        CstrBackground.setBackgroundResource(R.drawable.rain_background);
                        lottieAnimationView2.setAnimation(R.raw.rain);
                        lottieAnimationView2.playAnimation();
                    } else if(condition.toLowerCase().equals("snow") || condition.toLowerCase().equals("light snow") || condition.toLowerCase().equals("moderate snow") ||
                            condition.toLowerCase().equals("heavy snow") || condition.toLowerCase().equals("blizzard")){
                        CstrBackground.setBackgroundResource(R.drawable.snow_background);
                        lottieAnimationView2.setAnimation(R.raw.snow);
                        lottieAnimationView2.playAnimation();
                    }
                    //thu ngay thang nam
                    String sNgay = response.getString("dt");
                    long lNgay = Long.parseLong(sNgay);
                    // thứ
                    @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE");
                    Date date = new Date(lNgay * 1000);
                    String dt = dateFormat.format(date);
                    tvDay.setText(dt);
                    // ngay thang
                    SimpleDateFormat dateFormatNgay = new SimpleDateFormat("dd,MMMM,yyyy", new Locale("en"));
                    Date dateNgay = new Date(lNgay * 1000);
                    String dt1 = dateFormatNgay.format(dateNgay);
                    tvDate.setText(dt1);
                    //sunrise
                    String sGio = response.getJSONObject("sys").getString("sunrise");
                    long lGio = Long.parseLong(sGio);
                    @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormatSunrise = new SimpleDateFormat("HH:mm");
                    Date dateSunrise = new Date(lGio * 1000);
                    String sunrise = dateFormatSunrise.format(dateSunrise);
                    tvSunrise.setText(sunrise);
                    //sunset
                    String sSet = response.getJSONObject("sys").getString("sunset");
                    long lSet = Long.parseLong(sSet);
                    @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormatSunset = new SimpleDateFormat("HH:mm");
                    Date dateSunset = new Date(lSet * 1000);
                    String sunset = dateFormatSunset.format(dateSunset);
                    tvSunset.setText(sunset);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //that bai chay ham nay
                Toast.makeText(MainActivity.this, "API error", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonObjectRequest);

    }


//    public void weatherNextday(View view) {
//        Intent intent = new Intent(MainActivity.this, NextDayActivity.class);
//        intent.putExtra("city", city);
//        startActivity(intent);
//    }
}
