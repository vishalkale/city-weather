package com.vishalvdk.cityweather.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vishalvdk.cityweather.R;
import com.vishalvdk.cityweather.model.WeatherInfo;
import com.vishalvdk.cityweather.util.CommonUtil;
import com.vishalvdk.cityweather.util.Constants;

public class DetailActivity extends AppCompatActivity {
    private ImageView mIconView;
    private TextView mDateView;
    private TextView mDescriptionView;
    private TextView mHighTempView;
    private TextView mLowTempView;
    private TextView mWindView;
    private TextView mHumidityView;
    private TextView mPressureView;

    public final static String CITY_WEATHER_ARG = "CityWeather";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mIconView = (ImageView) findViewById(R.id.detail_icon);
        mDateView = (TextView) findViewById(R.id.detail_date_textview);
        mDescriptionView = (TextView) findViewById(R.id.detail_forecast_textview);
        mHighTempView = (TextView) findViewById(R.id.detail_high_textview);
        mLowTempView = (TextView) findViewById(R.id.detail_low_textview);
        mHumidityView = (TextView) findViewById(R.id.detail_humidity_textview);
        mWindView = (TextView) findViewById(R.id.detail_wind_textview);
        mPressureView = (TextView) findViewById(R.id.detail_pressure_textview);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("Bundle");
        WeatherInfo weatherInfo = (WeatherInfo) bundle.getSerializable(CITY_WEATHER_ARG);
        setupUi(weatherInfo);
    }

    private void setupUi(WeatherInfo weatherInfo) {
        long weatherId = weatherInfo.weather.get(0).id;
        Glide.with(this)
                .load(Constants.FORECAST_BASE_URL + weatherInfo.weather.get(0).icon + ".png")
                .placeholder(R.drawable.ic_clear)
                .fitCenter()
                .into(mIconView);
        String description = weatherInfo.weather.get(0).description;
        mIconView.setContentDescription(description);
        mDescriptionView.setText(description);

        mDateView.setText(CommonUtil.formatDate(this, weatherInfo.dt));

        double high = weatherInfo.temp.max;
        mHighTempView.setText(getString(R.string.format_temperature, high));

        double low = weatherInfo.temp.min;
        mLowTempView.setText(getString(R.string.format_temperature, low));

        double humidity = weatherInfo.humidity;
        mHumidityView.setText(getString(R.string.format_humidity, humidity));

        double windSpeedStr = weatherInfo.clouds;
        double windDirStr = weatherInfo.deg;
        mWindView.setText(getString(R.string.format_wind, windSpeedStr, windDirStr));

        double pressure = weatherInfo.pressure;
        mPressureView.setText(getString(R.string.format_pressure, pressure));

    }

}
