package com.example.almaz.gdgkazanweather.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by almaz on 15.06.2016.
 */
public class Forecast {
    @SerializedName("coord")
    public Coord coord;
    @SerializedName("sys")
    public Sys sys;
    @SerializedName("weather")
    public Weather weather;
    @SerializedName("base")
    public String base;
    @SerializedName("main")
    public Main main;
    @SerializedName("wind")
    public Wind wind;
    @SerializedName("clouds")
    public Clouds clouds;
    @SerializedName("dt")
    public String dt;
    @SerializedName("id")
    public long id;
    @SerializedName("name")
    public String name;
    @SerializedName("cod")
    public long cod;

    public static class Coord{
        @SerializedName("lon")
        public double lon;
        @SerializedName("lat")
        public double lat;
    }

    public static class Sys{
        @SerializedName("message")
        public String message;
        @SerializedName("country")
        public String country;
        @SerializedName("sunrise")
        public long sunrise;
        @SerializedName("sunset")
        public long sunset;
    }

    public static class Weather{
        @SerializedName("id")
        public long id;
        @SerializedName("main")
        public String main;
        @SerializedName("description")
        public String description;
        @SerializedName("icon")
        public String icon;
    }

    public static class Main{
        @SerializedName("temp")
        public double temp;
        @SerializedName("humidity")
        public double humidity;
        @SerializedName("pressure")
        public double pressure;
        @SerializedName("temp_min")
        public double temp_min;
        @SerializedName("temp_max")
        public double temp_max;
    }

    public static class Wind{
        @SerializedName("speed")
        public double speed;
        @SerializedName("deg")
        public double deg;
    }

    public static class Clouds{
        @SerializedName("all")
        public int all;
    }


}
