package com.github.zhukdi.your_tour;

import android.os.AsyncTask;

import com.github.zhukdi.your_tour.helper.DownloadUrl;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Dmitry on 4/26/2018.
 */

public class GetDirectionsData extends AsyncTask<Object, String, String> {

    private GoogleMap mMap;
    private String url;
    private String googleDirectionsData;
    private String duration, distance;
    private LatLng latLng;

    @Override
    protected String doInBackground(Object... objects) {
        mMap = (GoogleMap) objects[0];
        url = (String) objects[1];
        latLng  = (LatLng) objects[2];

        try {
            googleDirectionsData = DownloadUrl.readUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return googleDirectionsData;
    }

    @Override
    protected void onPostExecute(String s) {
        HashMap<String, String> directionsList = null;
        DataParser dataParser = new DataParser();
        directionsList = dataParser.parseDirections(s);
        duration = directionsList.get("duration");
        distance = directionsList.get("distance");

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        markerOptions.title("Duration = " + duration);
        markerOptions.snippet("Distance = " + distance);

        mMap.addMarker(markerOptions);
    }
}
