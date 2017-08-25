package com.radodosev.fivedayweather.weatherforecast;

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter;
import com.radodosev.fivedayweather.data.remote.WeatherForecastRemoteDataSource;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Rado on 8/24/2017.
 */

public class WeatherForecastPresenter extends MviBasePresenter<WeatherForecastView, WeatherForecastViewState> {

    private final WeatherForecastRemoteDataSource remoteDataSource;

    public WeatherForecastPresenter(final WeatherForecastRemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    protected void bindIntents() {
        // On sending loadAllWalks intent from the view this code block loads all walks from the local DB
        // and then sends them to the view to be rendered
        final Observable<WeatherForecastViewState> loadTheForecast =
                intent(WeatherForecastView::loadWeatherForecast)
                        .doOnNext(ignore -> Timber.d("intent: WeatherForecastView::loadWeatherForecast"))
                        .flatMap(ignore -> remoteDataSource.get5DayWeatherForecast("Burgas", "BG")
                                .map(WeatherForecastViewState::DATA_LOADED)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .startWith(WeatherForecastViewState.LOADING())
                                .onErrorReturn(WeatherForecastViewState::ERROR));

        subscribeViewState(loadTheForecast, WeatherForecastView::render);
    }
}
