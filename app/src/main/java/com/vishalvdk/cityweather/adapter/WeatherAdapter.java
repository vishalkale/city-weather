package com.vishalvdk.cityweather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vishalvdk.cityweather.R;
import com.vishalvdk.cityweather.model.Weather;
import com.vishalvdk.cityweather.model.WeatherInfo;
import com.vishalvdk.cityweather.util.CommonUtil;
import com.vishalvdk.cityweather.util.Constants;

import java.util.List;

/**
 * Created by vdkale on 14/10/15.
 */

public class WeatherAdapter extends BaseAdapter {
    private final Context context;
    private final List<WeatherInfo> weatherInfoList;

    public WeatherAdapter(Context context, List<WeatherInfo> weatherInfoList) {
        this.context = context;
        this.weatherInfoList = weatherInfoList;
    }

    @Override
    public int getCount() {
        return weatherInfoList.size();
    }

    @Override
    public WeatherInfo getItem(int position) {
        return weatherInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_weather, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        WeatherInfo weatherInfo = getItem(position);
        Weather weather = weatherInfo.weather.get(0);
        viewHolder.dateView.setText(CommonUtil.formatDate(context, weatherInfo.dt));
        viewHolder.descriptionView.setText(weather.description);
        //viewHolder.iconView.setImageResource(CommonUtil.getIconResourceForWeatherCondition(weather.id));
        Glide.with(context)
                .load(Constants.FORECAST_BASE_URL + weatherInfo.weather.get(0).icon + ".png")
                .placeholder(R.drawable.ic_clear)
                .into(viewHolder.iconView);
        viewHolder.highTempView.setText(context.getString(R.string.format_temperature, weatherInfo.temp.max));
        viewHolder.lowTempView.setText(context.getString(R.string.format_temperature, weatherInfo.temp.min));
        return convertView;
    }

    public static class ViewHolder {
        public final ImageView iconView;
        public final TextView dateView;
        public final TextView descriptionView;
        public final TextView highTempView;
        public final TextView lowTempView;

        public ViewHolder(View view) {
            iconView = (ImageView) view.findViewById(R.id.list_item_icon);
            dateView = (TextView) view.findViewById(R.id.list_item_date_textview);
            descriptionView = (TextView) view.findViewById(R.id.list_item_forecast_textview);
            highTempView = (TextView) view.findViewById(R.id.list_item_high_textview);
            lowTempView = (TextView) view.findViewById(R.id.list_item_low_textview);
        }

    }
}


