package com.vishalvdk.cityweather.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.vishalvdk.cityweather.ui.WeatherFragment;

import java.util.List;

/**
 * Created by vdkale on 13/10/15.
 */
public class CityPagerAdapter extends FragmentStatePagerAdapter {

    private final List<String> cityList;

    public CityPagerAdapter(FragmentManager fm, List<String> cityList) {
        super(fm);
        this.cityList = cityList;
    }

    @Override
    public Fragment getItem(int position) {
        WeatherFragment weatherFragment = new WeatherFragment();
        Bundle bundle = new Bundle();
        bundle.putString(WeatherFragment.CITY_NAME, cityList.get(position));
        weatherFragment.setArguments(bundle);
        return weatherFragment;
    }

    @Override
    public int getCount() {
        return cityList.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return cityList.get(position);
    }

}
