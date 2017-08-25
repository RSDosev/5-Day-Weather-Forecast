package com.radodosev.fivedayweather.weatherforecast;

import android.support.annotation.IntDef;

import com.radodosev.fivedayweather.data.model.WeatherForecastData;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.radodosev.fivedayweather.weatherforecast.WeatherForecastViewState.State.DATA_LOADED;
import static com.radodosev.fivedayweather.weatherforecast.WeatherForecastViewState.State.ERROR;
import static com.radodosev.fivedayweather.weatherforecast.WeatherForecastViewState.State.LOADING;

/**
 * Created by Rado on 7/8/2017.
 */

class WeatherForecastViewState {
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({LOADING, DATA_LOADED, ERROR})
    public @interface State {
        int LOADING = 1;
        int DATA_LOADED = 2;
        int ERROR = 3;
    }

    private final
    @State
    int state;
    private final Throwable error;
    private final WeatherForecastData forecastData;

    WeatherForecastViewState(final @State int state,
                             final Throwable error,
                             final WeatherForecastData forecastData) {
        this.state = state;
        this.error = error;
        this.forecastData = forecastData;
    }

    public
    @State
    int getState() {
        return state;
    }

    public Throwable getError() {
        return error;
    }

    public WeatherForecastData getForecastData() {
        return forecastData;
    }

    public static WeatherForecastViewState ERROR(Throwable error) {
        return new WeatherForecastViewState(ERROR, error, null);
    }

    public static WeatherForecastViewState LOADING() {
        return new WeatherForecastViewState(LOADING, null, null);
    }

    public static WeatherForecastViewState DATA_LOADED(WeatherForecastData data) {
        return new WeatherForecastViewState(DATA_LOADED, null, data);
    }
}
