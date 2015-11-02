package com.vishalvdk.cityweather.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.vishalvdk.cityweather.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<String> cityList;
    private ArrayAdapter<String> cityListAdapter;
    private EditText cityEditText;
    private ListView cityListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        cityEditText = (EditText) findViewById(R.id.city);
        ImageView add = (ImageView) findViewById(R.id.add);
        FloatingActionButton go = (FloatingActionButton) findViewById(R.id.go);
        add.setOnClickListener(this);
        go.setOnClickListener(this);
        cityListView = (ListView) findViewById(R.id.listView);

        cityList = new ArrayList<>();
        cityListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cityList);
        cityListView.setAdapter(cityListAdapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                String cityName = cityEditText.getText().toString();
                addCity(v, cityName);
                cityEditText.setText("");
                break;
            case R.id.go:
                gotoWeather(v);
                break;
        }
    }

    private void gotoWeather(View v) {
        if (cityList.size() > 0) {
            Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
            intent.putStringArrayListExtra(WeatherActivity.CITY_LIST_ARG, cityList);
            startActivity(intent);
        } else {
            Snackbar.make(v, R.string.add_city_err_msg, Snackbar.LENGTH_LONG).show();
        }

    }

    public void addCity(View v, String cityName) {
        if (!cityName.isEmpty()) {
            cityList.add(cityName);
            cityListAdapter.notifyDataSetChanged();
            Snackbar.make(v, R.string.city_added, Snackbar.LENGTH_LONG).show();
        } else {
            Snackbar.make(v, R.string.enter_city_name, Snackbar.LENGTH_LONG).show();
        }
    }
}
