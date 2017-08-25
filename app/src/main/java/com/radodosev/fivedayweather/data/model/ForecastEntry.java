package com.radodosev.fivedayweather.data.model;

import java.io.Serializable;

/**
 * Created by Rado on 8/24/2017.
 */

public class ForecastEntry implements Serializable {
    private static final long serialVersionUID = -107557847858738581L;

    private String dt;
    private String dt_txt;
    private MainInfo main;
    private WeatherInfo[] weather;

    public ForecastEntry(String dt, String dt_txt, MainInfo main, WeatherInfo[] weather) {
        this.dt = dt;
        this.dt_txt = dt_txt;
        this.main = main;
        this.weather = weather;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public MainInfo getMain() {
        return main;
    }

    public void setMain(MainInfo main) {
        this.main = main;
    }

    public WeatherInfo[] getWeather() {
        return weather;
    }

    public void setWeather(WeatherInfo[] weather) {
        this.weather = weather;
    }

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }


    public static class MainInfo implements Serializable{
        private static final long serialVersionUID = 684916574512223536L;

        private float temp;
        private float temp_min;
        private float temp_max;
        private float pressure;
        private float humidity;

        public MainInfo(float temp, float temp_min, float temp_max, float pressure, float humidity) {
            this.temp = temp;
            this.temp_min = temp_min;
            this.temp_max = temp_max;
            this.pressure = pressure;
            this.humidity = humidity;
        }

        public float getTemp() {
            return temp;
        }

        public void setTemp(float temp) {
            this.temp = temp;
        }

        public float getTemp_min() {
            return temp_min;
        }

        public void setTemp_min(float temp_min) {
            this.temp_min = temp_min;
        }

        public float getTemp_max() {
            return temp_max;
        }

        public void setTemp_max(float temp_max) {
            this.temp_max = temp_max;
        }

        public float getPressure() {
            return pressure;
        }

        public void setPressure(float pressure) {
            this.pressure = pressure;
        }

        public float getHumidity() {
            return humidity;
        }

        public void setHumidity(float humidity) {
            this.humidity = humidity;
        }
    }

    public static class WeatherInfo implements Serializable{
        private static final long serialVersionUID = 7921692528138327391L;

        private String main;
        private String description;

        public WeatherInfo(String main, String description) {
            this.main = main;
            this.description = description;
        }

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
