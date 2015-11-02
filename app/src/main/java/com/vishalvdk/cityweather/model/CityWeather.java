package com.vishalvdk.cityweather.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CityWeather implements Serializable {

    public City city;
    public String cod;
    public double message;
    public int cnt;
    public List<WeatherInfo> weatherInfo = new ArrayList<WeatherInfo>();
}