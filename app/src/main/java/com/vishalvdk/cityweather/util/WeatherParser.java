package com.vishalvdk.cityweather.util;

import com.vishalvdk.cityweather.model.CityWeather;

import org.json.JSONObject;

/**
 * Created by vdkale on 28/10/15.
 */
public interface WeatherParser {
    public CityWeather toCityWeather(JSONObject jsonObject);
}
