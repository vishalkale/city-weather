package com.vishalvdk.cityweather.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.vishalvdk.cityweather.R;
import com.vishalvdk.cityweather.adapter.WeatherAdapter;
import com.vishalvdk.cityweather.model.CityWeather;
import com.vishalvdk.cityweather.model.WeatherInfo;
import com.vishalvdk.cityweather.network.WebServiceRequest;
import com.vishalvdk.cityweather.util.Constants;
import com.vishalvdk.cityweather.util.JsonParser;
import com.vishalvdk.cityweather.util.WeatherParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class WeatherFragment extends Fragment implements WebServiceRequest.ResponseListener, AdapterView.OnItemClickListener {

    public final static String CITY_NAME = "CityName";
    private static final String TAG = "WeatherFragment";
    private final int numDays = 14;
    List<WeatherInfo> weatherInfoList;
    private ProgressBar progressBar;
    private ListView listView;
    private WeatherAdapter weatherAdapter;
    private String cityName;

    public WeatherFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_weather, container, false);

        progressBar = (ProgressBar) v.findViewById(R.id.progress);
        listView = (ListView) v.findViewById(R.id.listview_forecast);
        Bundle bundle = getArguments();
        cityName = bundle.getString(CITY_NAME);
        WebServiceRequest webServiceRequest = new WebServiceRequest(getActivity(), this);
        URL url = createUrl(cityName, numDays);
        webServiceRequest.execute(url);
        listView.setOnItemClickListener(this);
        return v;
    }

    private URL createUrl(String cityName, int numDays) {

        Uri builtUri = Uri.parse(Constants.FORECAST_BASE_URL).buildUpon()
                .appendQueryParameter(Constants.QUERY_PARAM, cityName)
                .appendQueryParameter(Constants.FORMAT_PARAM, Constants.format)
                .appendQueryParameter(Constants.UNITS_PARAM, Constants.units)
                .appendQueryParameter(Constants.DAYS_PARAM, Integer.toString(numDays))
                .appendQueryParameter(Constants.APP_ID, Constants.appId)
                .build();

        try {
            return new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onError() {
//        Log.d(TAG, "Error Code = " + error);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onSuccess(String response) {
        Log.d(TAG, response);
        progressBar.setVisibility(View.GONE);
        WeatherParser weatherParser = new JsonParser();
        try {
            CityWeather cityWeather = weatherParser.toCityWeather(new JSONObject(response));
            weatherInfoList = cityWeather.weatherInfo;
            if (weatherInfoList != null) {
                weatherAdapter = new WeatherAdapter(getActivity(), weatherInfoList);
                listView.setAdapter(weatherAdapter);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(DetailActivity.CITY_WEATHER_ARG, weatherInfoList.get(position));
        intent.putExtra("Bundle", bundle);
        startActivity(intent);
    }
}
