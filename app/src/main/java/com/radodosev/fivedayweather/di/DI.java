package com.radodosev.fivedayweather.di;

import android.app.Activity;
import android.content.Context;

import com.radodosev.fivedayweather.data.remote.APIService;
import com.radodosev.fivedayweather.data.remote.ApiServiceFactory;
import com.radodosev.fivedayweather.data.remote.WeatherForecastRemoteDataSource;
import com.radodosev.fivedayweather.weatherforecast.WeatherForecastActivity;
import com.radodosev.fivedayweather.weatherforecast.WeatherForecastPresenter;

/**
 * Created by Rado on 8/24/2017.
 */

public final class DI {
    private DI() {
    }

    public static APIService provideApiService() {
        return new ApiServiceFactory().createApiService();
    }

    public static WeatherForecastPresenter provideWeatherForecastPresenter() {
        return new WeatherForecastPresenter(provideRemoteDataSource());
    }

    public static WeatherForecastRemoteDataSource provideRemoteDataSource(){
        return WeatherForecastRemoteDataSource.get();
    }
}
