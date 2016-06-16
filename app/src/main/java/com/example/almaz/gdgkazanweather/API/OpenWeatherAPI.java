package com.example.almaz.gdgkazanweather.API;

import com.example.almaz.gdgkazanweather.Model.Forecast;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by almaz on 15.06.2016.
 */
public interface OpenWeatherAPI {

    @GET("/weather")
    Forecast getWeatherByLatLon(@Query("q") String name);

}