package com.example.almaz.gdgkazanweather.API;

import com.example.almaz.gdgkazanweather.Model.Forecast;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

/**
 * Created by almaz on 15.06.2016.
 */
public class WeatherRequest extends RetrofitSpiceRequest<Forecast, OpenWeatherAPI> {

    private String name;

    public WeatherRequest(String name) {
        super(Forecast.class, OpenWeatherAPI.class);
        this.name=name;
    }

    @Override
    public Forecast loadDataFromNetwork() throws Exception {

        Forecast mWeather = getService().getWeatherByLatLon(name);
        return mWeather;
    }
}