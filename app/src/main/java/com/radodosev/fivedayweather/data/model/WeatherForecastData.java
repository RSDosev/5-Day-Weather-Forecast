package com.radodosev.fivedayweather.data.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Rado on 8/24/2017.
 */

public class WeatherForecastData implements Serializable {
    private String cod;
    private String message;
    private ForecastEntry[] list;

    public WeatherForecastData(String cod, String message, ForecastEntry[] list) {
        this.cod = cod;
        this.message = message;
        this.list = list;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ForecastEntry[] getList() {
        return list;
    }

    public void setList(ForecastEntry[] list) {
        this.list = list;
    }
}
