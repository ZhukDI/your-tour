package com.github.zhukdi.your_tour.services;

import android.os.AsyncTask;

import com.github.zhukdi.your_tour.parsers.DataParser;
import com.github.zhukdi.your_tour.helpers.DownloadUrl;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Dmitry on 5/1/2018.
 */

public class GetPlaceDetailsData extends AsyncTask<Object, String, String> {

    String googlePlaceDetailsData;
    String url;

    @Override
    protected String doInBackground(Object... objects) {
        url = (String)objects[0];
        try {
            googlePlaceDetailsData = DownloadUrl.readUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("#################################");
        System.out.println(googlePlaceDetailsData);
        System.out.println("#################################");
        return googlePlaceDetailsData;
    }

    @Override
    protected void onPostExecute(String s) {
        HashMap<String, String> placeDetails = null;
        DataParser dataParser = new DataParser();
        placeDetails = dataParser.parsePlaceDetailsData(s);
        System.out.println("*******************");
    }
}
