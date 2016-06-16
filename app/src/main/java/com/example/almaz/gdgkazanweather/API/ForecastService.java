package com.example.almaz.gdgkazanweather.API;

import android.net.Uri;

import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;

import org.apache.commons.lang3.NotImplementedException;

import java.util.HashMap;
import java.util.Map;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

/**
 * Created by almaz on 15.06.2016.
 */
public class ForecastService extends RetrofitGsonSpiceService {

    private final String api_key = "dc895385a8050c4a49ae2244e6f39fba";
    RestAdapter.Builder restAdapterBuilder;
    private Map<Class<?>, Object> retrofitInterfaceToServiceMap = new HashMap<Class<?>, Object>();
    final String APPID_PARAM = "APPID";
    private String BACKEND_URL = "http://api.openweathermap.org/data/2.5";
    private Map<Class<?>, String> retrofitInterfaceToUrl = new HashMap<Class<?>, String>() {{
        put(OpenWeatherAPI.class, BACKEND_URL);
    }};

    @Override
    public void onCreate() {
        super.onCreate();
        restAdapterBuilder = new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(BACKEND_URL).setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addQueryParam(APPID_PARAM, api_key);
                    }
                });

    }

    @Override
    protected String getServerUrl() {
        throw new NotImplementedException("Method should not be used");
    }

    @Override
    protected RestAdapter.Builder createRestAdapterBuilder() {

        return new RestAdapter.Builder().setConverter(getConverter()).setEndpoint("http://google.com");
    }

    @Override
    protected <T> T getRetrofitService(Class<T> serviceClass) {
        T service = (T) retrofitInterfaceToServiceMap.get(serviceClass);
        if (service == null) {
            service = restAdapterBuilder.build().create(serviceClass);
            retrofitInterfaceToServiceMap.put(serviceClass, service);
        }
        return service;
    }
}
