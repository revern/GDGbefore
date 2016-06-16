package com.example.almaz.gdgkazanweather;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.almaz.gdgkazanweather.API.ForecastService;
import com.example.almaz.gdgkazanweather.API.WeatherRequest;
import com.example.almaz.gdgkazanweather.Model.Forecast;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

public class ForecastActivity extends AppCompatActivity {

    public static final String APP_PREFERENCES = "mySettings";
    public static final String EXTRA_CITY_NAME = "EXTRA CITY NAME";
    public static final String FORECAST = "FORECAST";

    private SpiceManager spiceManager = new SpiceManager(ForecastService.class);
    private Forecast forecast;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        sharedPreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        Intent intent = getIntent();
        processLocation(intent.getStringExtra(EXTRA_CITY_NAME));
        Toast.makeText(this, forecast.main.temp+"",Toast.LENGTH_LONG).show();
    }

    public void processLocation(String name) {

        WeatherRequest request = new WeatherRequest(name);
        spiceManager.execute(request, new RequestListener<Forecast>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {

            }

            @Override
            public void onRequestSuccess(Forecast currentForecast) {
                // TODO: here you can get all your weather parametrs
                forecast = currentForecast;
                try {
                    SharedPreferences.Editor ed = sharedPreferences.edit();
                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder.create();
                    ed.putString(FORECAST, gson.toString());
                    Log.d("TAG", currentForecast.name);
                } catch (Exception e) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ForecastActivity.this);
                    builder.setTitle("Wrong city name")
                            .setMessage("Please, enter correct city")
                            .setCancelable(false)
                            .setNegativeButton("OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
                Toast.makeText(getApplicationContext(),forecast.main.temp+"", Toast.LENGTH_LONG).show();

            }
        });
    }
}
