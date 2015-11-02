package com.vishalvdk.cityweather.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WeatherInfo implements Serializable{

    public long dt;
    public Temp temp;
    public double pressure;
    public double humidity;
    public List<Weather> weather = new ArrayList<>();
    public double speed;
    public int deg;
    public int clouds;

}