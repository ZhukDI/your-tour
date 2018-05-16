package com.github.zhukdi.your_tour;

import android.os.AsyncTask;

import com.github.zhukdi.your_tour.helper.DownloadUrl;
import com.github.zhukdi.your_tour.model.Place;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Dmitry on 4/21/2018.
 */

public class GetNearbyPlacesData extends AsyncTask<Object, String, String> {

    String googlePlacesData;
//    ArrayList<Place> googlePlaces;
    GoogleMap mMap;
    String url;

    @Override
    protected String doInBackground(Object... objects) {
//        googlePlaces = (ArrayList<Place>)objects[0];
        mMap = (GoogleMap)objects[0];
        url = (String)objects[1];

        try {
            googlePlacesData = DownloadUrl.readUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return googlePlacesData;
//        return googlePlaces;

    }

    @Override
    protected void onPostExecute(String s) {
        List<Place> nearbyPlaceList = null;
        DataParser dataParser = new DataParser();
        nearbyPlaceList = dataParser.parsePlaceList(s);
//        googlePlaces.addAll(dataParser.parsePlaceList(s));
        showNearbyPlaces(nearbyPlaceList);
    }



    private void showNearbyPlaces(List<Place> nearbyPlacesList) {
        for (int i = 0;  i < nearbyPlacesList.size(); i++) {
            MarkerOptions markerOptions = new MarkerOptions();
            Place place = nearbyPlacesList.get(i);
//            System.out.println(googlePlace);

//            String placeId = googlePlace.get("id");
//            String placeName = googlePlace.get("place_name");
//            double lat = Double.parseDouble(googlePlace.get("lat"));
//            double lng = Double.parseDouble(googlePlace.get("lng"));
//            String vicinity = googlePlace.get("vicinity");
//            Place place = new Place(placeId, placeName, lat, lng, vicinity);

            LatLng latLng = new LatLng(place.getLat(), place.getLng());
            markerOptions.position(latLng);
            markerOptions.title(place.getName() + " : " + place.getVicinity());
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
//            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.map_pin_places));

//            if (placeName.equals("restaurant")) {
//                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_restaurant_map));
//            } else {
//                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
//            }

            mMap.addMarker(markerOptions);
        }
    }
}
