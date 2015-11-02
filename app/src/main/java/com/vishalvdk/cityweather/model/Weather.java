package com.vishalvdk.cityweather.model;

import java.io.Serializable;

public class Weather implements Serializable{
    public long id;
    public String main;
    public String description;
    public String icon;
}