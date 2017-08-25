package com.radodosev.fivedayweather.data.remote;

import com.radodosev.fivedayweather.data.WeatherForecastDataSource;
import com.radodosev.fivedayweather.data.model.WeatherForecastData;
import com.radodosev.fivedayweather.di.DI;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Rado on 8/24/2017.
 */

public class WeatherForecastRemoteDataSource implements WeatherForecastDataSource {
    private static WeatherForecastRemoteDataSource instance;
    private APIService apiService;

    private WeatherForecastRemoteDataSource(APIService apiService) {
        this.apiService = apiService;
    }

    public static WeatherForecastRemoteDataSource get() {
        if (instance == null) {
            synchronized (WeatherForecastRemoteDataSource.class) {
                if (instance == null) {
                    instance = new WeatherForecastRemoteDataSource(DI.provideApiService());
                }
            }
        }
        return instance;
    }

    public Observable<WeatherForecastData> get5DayWeatherForecast(String city, String countryCode){
        return apiService.getWeatherForecast(APIService.KEY,city + "," + countryCode);
    }
}
