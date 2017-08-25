package com.radodosev.fivedayweather.weatherforecast.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.radodosev.fivedayweather.R;
import com.radodosev.fivedayweather.data.model.ForecastEntry;
import com.radodosev.fivedayweather.util.CommonUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rado on 8/25/2017.
 */

public class DayForecastAdapter extends BaseAdapter {
    private final Context context;
    private final List<ForecastEntry> data;

    public DayForecastAdapter(final Context context, final List<ForecastEntry> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DayForecastViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_hour_forecast, null);
            holder = new DayForecastViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (DayForecastViewHolder) convertView.getTag();
        }

        holder.bind(data.get(position));
        return convertView;
    }


    public class DayForecastViewHolder {
        @BindView(R.id.text_view_date)
        TextView date;
        @BindView(R.id.text_view_weather_title)
        TextView title;
        @BindView(R.id.text_view_weather_description)
        TextView description;
        @BindView(R.id.text_view_temp)
        TextView temperature;
        @BindView(R.id.text_view_pressure)
        TextView pressure;
        @BindView(R.id.text_view_humidity)
        TextView humidity;

        public DayForecastViewHolder(final View rootView) {
            ButterKnife.bind(this, rootView);
        }

        void bind(final ForecastEntry forecastEntry) {
            final Calendar calendar = Calendar.getInstance();
            calendar.setTime(CommonUtils.parseDate(forecastEntry.getDt_txt()));

            final String hour = String.valueOf(calendar.get(Calendar.HOUR));
            final String amPM = calendar.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
            date.setText(hour + " " + amPM);

            title.setText(forecastEntry.getWeather()[0].getMain());
            description.setText(forecastEntry.getWeather()[0].getDescription());
            temperature.setText(String.format("%d°F (%d°F | %d°F)",
                    Math.round(forecastEntry.getMain().getTemp()),
                    Math.round(forecastEntry.getMain().getTemp_min()),
                            Math.round(forecastEntry.getMain().getTemp_max())));
            pressure.setText(String.format("%d hPa", Math.round(forecastEntry.getMain().getPressure())));
            humidity.setText(String.format("%d%%", Math.round(forecastEntry.getMain().getPressure())));
        }
    }
}
