package com.github.zhukdi.your_tour.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.github.zhukdi.your_tour.settings.AppSettings.OPEN_WEATHER_MAP_KEY;

/**
 * Created by Dmitry on 3/31/2018.
 */

public class Common {

    public static String API_LINK = "http://api.openweathermap.org/data/2.5/weather";

    public static String apiRequest(String lat, String lng) {
        StringBuilder sb = new StringBuilder(API_LINK);
        sb.append(String.format("?lat=%s&lon=%s&APPID=%s&units=metric", lat, lng, OPEN_WEATHER_MAP_KEY));
        return sb.toString();
    }

    // convert unix time stamp to Date type with format HH:mm
    public static String timeConverter(double unixTimeStamp) {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        date.setTime((long)unixTimeStamp * 1000);
        return dateFormat.format(date);
    }

    // get a link image from OpenWeatherMap
    public static String getImage(String icon) {
        return String.format("http://openweathermap.org/img/w/%s.png", icon);
    }

    // get current date
    public static String getDateNow() {
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm");
        Date date = new Date();
        return dateFormat.format(date);
    }



}
