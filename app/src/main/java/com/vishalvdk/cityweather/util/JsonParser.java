package com.vishalvdk.cityweather.util;

import com.vishalvdk.cityweather.model.City;
import com.vishalvdk.cityweather.model.CityWeather;
import com.vishalvdk.cityweather.model.LatLong;
import com.vishalvdk.cityweather.model.Temp;
import com.vishalvdk.cityweather.model.Weather;
import com.vishalvdk.cityweather.model.WeatherInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vdkale on 14/10/15.
 */
public class JsonParser implements WeatherParser {

    public CityWeather toCityWeather(JSONObject jsonObject) {
        if (jsonObject == null)
            return null;
        try {
            CityWeather cityWeather = new CityWeather();
            JSONObject jsonCity = jsonObject.getJSONObject("city");
            cityWeather.city = toCity(jsonCity);
            cityWeather.cod = jsonObject.getString("cod");
            cityWeather.message = jsonObject.getDouble("message");
            cityWeather.cnt = jsonObject.getInt("cnt");
            cityWeather.weatherInfo = toInfoList(jsonObject.getJSONArray("list"));
            return cityWeather;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<WeatherInfo> toInfoList(JSONArray jsonArray) {
        List<WeatherInfo> weatherInfoList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonWeather = jsonArray.getJSONObject(i);
                WeatherInfo weatherInfo = new WeatherInfo();
                weatherInfo.dt = jsonWeather.getLong("dt");
                weatherInfo.temp = toTemp(jsonWeather.getJSONObject("temp"));
                weatherInfo.pressure = jsonWeather.getDouble("pressure");
                weatherInfo.humidity = jsonWeather.getInt("humidity");
                weatherInfo.weather = toWeatherList(jsonWeather.getJSONArray("weather"));
                weatherInfo.speed = jsonWeather.getDouble("speed");
                weatherInfo.deg = jsonWeather.getInt("deg");
                weatherInfo.clouds = jsonWeather.getInt("clouds");
                weatherInfoList.add(weatherInfo);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return weatherInfoList;
    }

    private List<Weather> toWeatherList(JSONArray jsonArray) {
        List<Weather> weatherList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonWeather = jsonArray.getJSONObject(i);
                Weather weather = new Weather();
                weather.id = jsonWeather.getLong("id");
                weather.main = jsonWeather.getString("main");
                weather.description = jsonWeather.getString("description");
                weather.icon = jsonWeather.getString("icon");
                weatherList.add(weather);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return weatherList;
    }

    private Temp toTemp(JSONObject jsonTemp) {
        try {
            Temp temp = new Temp();
            temp.day = jsonTemp.getDouble("day");
            temp.min = jsonTemp.getDouble("min");
            temp.max = jsonTemp.getDouble("max");
            temp.night = jsonTemp.getDouble("night");
            temp.eve = jsonTemp.getDouble("eve");
            temp.morn = jsonTemp.getDouble("morn");
            return temp;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private City toCity(JSONObject jsonCity) {
        try {
            City city = new City();
            city.id = jsonCity.getLong("id");
            city.name = jsonCity.getString("name");
            city.country = jsonCity.getString("country");
            city.population = jsonCity.getLong("population");
            JSONObject jsonLatLong = jsonCity.getJSONObject("coord");
            city.latLong = toLatLong(jsonLatLong);
            return city;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private LatLong toLatLong(JSONObject jsonLatLong) {
        try {
            LatLong latLong = new LatLong();
            latLong.lat = jsonLatLong.getDouble("lat");
            latLong.lon = jsonLatLong.getDouble("lon");
            return latLong;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

}
