package com.vishalvdk.cityweather.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.vishalvdk.cityweather.R;
import com.vishalvdk.cityweather.adapter.CityPagerAdapter;
import com.vishalvdk.cityweather.util.Constants;

import java.util.List;

public class WeatherActivity extends AppCompatActivity {

    public static final int OFFSCREEN_LIMIT = 2;
    public static final String CITY_LIST_ARG = "CityList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        List<String> cityList = intent.getStringArrayListExtra(CITY_LIST_ARG);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new CityPagerAdapter(getSupportFragmentManager(),
                cityList));
        viewPager.setOffscreenPageLimit(OFFSCREEN_LIMIT);

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

}
