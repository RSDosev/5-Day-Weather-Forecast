package com.radodosev.fivedayweather.weatherforecast;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hannesdorfmann.mosby3.mvi.MviActivity;
import com.radodosev.fivedayweather.R;
import com.radodosev.fivedayweather.data.model.ForecastEntry;
import com.radodosev.fivedayweather.data.model.WeatherForecastData;
import com.radodosev.fivedayweather.di.DI;
import com.radodosev.fivedayweather.util.CommonUtils;
import com.radodosev.fivedayweather.weatherforecast.adapter.DayForecastAdapter;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

import static com.radodosev.fivedayweather.weatherforecast.WeatherForecastViewState.State.DATA_LOADED;
import static com.radodosev.fivedayweather.weatherforecast.WeatherForecastViewState.State.ERROR;
import static com.radodosev.fivedayweather.weatherforecast.WeatherForecastViewState.State.LOADING;

public class WeatherForecastActivity extends MviActivity<WeatherForecastView, WeatherForecastPresenter>
        implements WeatherForecastView {
    // ----- Instance fields -----
    @BindView(R.id.layout_forecast_day_1)
    View forecastDay1View;
    @BindView(R.id.layout_forecast_day_2)
    View forecastDay2View;
    @BindView(R.id.layout_forecast_day_3)
    View forecastDay3View;
    @BindView(R.id.layout_forecast_day_4)
    View forecastDay4View;
    @BindView(R.id.layout_forecast_day_5)
    View forecastDay5View;
    @BindView(R.id.view_loading)
    View loadingView;

    // ----- Activity lifecycle logic -----
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);
        ButterKnife.bind(this);
    }

    // ----- Creating the presenter -----
    @NonNull
    @Override
    public WeatherForecastPresenter createPresenter() {
        return DI.provideWeatherForecastPresenter();
    }

    // ----- Exposing the view intents-----
    @Override
    public Observable<Boolean> loadWeatherForecast() {
        return Observable.just(true);
    }


    // ----- Rendering the view state -----
    @Override
    public void render(WeatherForecastViewState viewState) {
        switch (viewState.getState()) {
            case LOADING:
                renderLoading();
                break;
            case DATA_LOADED:
                renderDataLoaded(viewState.getForecastData());
                break;
            case ERROR:
                renderError(viewState.getError());
                break;
        }
    }

    private void renderLoading() {
        loadingView.setVisibility(View.VISIBLE);
        forecastDay1View.setVisibility(View.GONE);
        forecastDay2View.setVisibility(View.GONE);
        forecastDay3View.setVisibility(View.GONE);
        forecastDay4View.setVisibility(View.GONE);
        forecastDay5View.setVisibility(View.GONE);
    }

    private void renderDataLoaded(final WeatherForecastData forecastData) {
        loadingView.setVisibility(View.GONE);
        forecastDay1View.setVisibility(View.VISIBLE);
        forecastDay2View.setVisibility(View.VISIBLE);
        forecastDay3View.setVisibility(View.VISIBLE);
        forecastDay4View.setVisibility(View.VISIBLE);
        forecastDay5View.setVisibility(View.VISIBLE);


        final List<ForecastEntry> forecastEntries = Arrays.asList(forecastData.getList());
        setUpSingleDayForecastView(forecastDay1View, forecastEntries.subList(0, 8));
        setUpSingleDayForecastView(forecastDay2View, forecastEntries.subList(8, 16));
        setUpSingleDayForecastView(forecastDay3View, forecastEntries.subList(16, 24));
        setUpSingleDayForecastView(forecastDay4View, forecastEntries.subList(24, 32));
        setUpSingleDayForecastView(forecastDay5View, forecastEntries.subList(32, 40));
    }

    private void setUpSingleDayForecastView(final View forecastDayView, final List<ForecastEntry> data) {
        final View headerView = ButterKnife.findById(forecastDayView, R.id.layout_day_forecast_header);
        final TextView date = ButterKnife.findById(headerView, R.id.text_view_date);
        date.setText(CommonUtils.formatDate(CommonUtils.parseDate(data.get(0).getDt_txt())));
        final TextView dailyTemp = ButterKnife.findById(headerView, R.id.text_view_temp);
        dailyTemp.setText(getMinMaxTempForDay(data));

        final ListView hourlyForecastView = ButterKnife.findById(forecastDayView, R.id.recycle_view_hourly_forecast);

        headerView.setOnClickListener(view -> hourlyForecastView.setVisibility(
                hourlyForecastView.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE));

        hourlyForecastView.setAdapter(new DayForecastAdapter(this, data));
        hourlyForecastView.setOnTouchListener((v, event) -> {
            // Disallow the touch request for parent scroll on touch of child view
            v.getParent().requestDisallowInterceptTouchEvent(true);
            return false;
        });
    }

    private String getMinMaxTempForDay(final List<ForecastEntry> data) {
        float minTemp = 9999999;
        float maxTemp = 0;
        for (ForecastEntry entry : data) {
            if (entry.getMain().getTemp_min() < minTemp)
                minTemp = entry.getMain().getTemp_min();

            if (entry.getMain().getTemp_max() > maxTemp)
                maxTemp = entry.getMain().getTemp_max();
        }

        return String.format("%d°F | %d°F",
                Math.round(minTemp),
                Math.round(maxTemp));
    }

    private void renderError(final Throwable error) {
        loadingView.setVisibility(View.GONE);

        Toast.makeText(this, error.getMessage(), Toast.LENGTH_LONG).show();
    }
}
