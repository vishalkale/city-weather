package com.vishalvdk.cityweather.util;

import android.content.Context;

import com.vishalvdk.cityweather.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by vdkale on 14/10/15.
 */
public class CommonUtil {

    public static String formatDate(Context context,long timestamp) {

        Date today = new Date();
        Date date = new Date(timestamp * 1000);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        String dateStr = simpleDateFormat.format(date);
        String todayStr = simpleDateFormat.format(today);
        if (dateStr.equals(todayStr))
            return context.getString(R.string.today);
        else {

            Calendar cal = Calendar.getInstance();
            cal.setTime(today);
            cal.add(Calendar.DATE, 7);
            String weekFutureStr = simpleDateFormat.format(cal.getTime());

            if (dateStr.compareTo(weekFutureStr) < 0) {
                // If the input date is less than a week in the future, just return the day name.
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(today);
                calendar.add(Calendar.DATE, 1);
                Date tomorrowDate = calendar.getTime();
                if (dateStr.equals(simpleDateFormat.format(tomorrowDate))) {
                    return context.getString(R.string.tomorrow);
                } else {
                    // Otherwise, the format is just the day of the week (e.g "Wednesday".
                    SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.getDefault());
                    return dayFormat.format(timestamp * 1000);
                }
            } else {
                // Otherwise, use the form "Mon Jun 3"
                SimpleDateFormat shortenedDateFormat = new SimpleDateFormat("EEE MMM dd", Locale.getDefault());
                return shortenedDateFormat.format(timestamp * 1000);
            }

        }
    }

}
