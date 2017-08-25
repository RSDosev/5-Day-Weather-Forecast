package com.radodosev.fivedayweather.data.remote;

import com.radodosev.fivedayweather.BuildConfig;
import com.radodosev.fivedayweather.data.model.WeatherForecastData;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by stanislav on 6/14/16.
 */
public interface APIService {

    String BASE_URL = BuildConfig.API_BASE_URL;
    String KEY = BuildConfig.API_KEY;

    @GET("forecast")
    Observable<WeatherForecastData> getWeatherForecast(
            @Query("appid") String apiKey,
            @Query("q") String cityAndCountryCode
    );
}
