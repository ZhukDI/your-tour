package com.github.zhukdi.your_tour;

import android.os.AsyncTask;

import com.github.zhukdi.your_tour.helper.DownloadUrl;
import com.github.zhukdi.your_tour.model.Place;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Dmitry on 4/21/2018.
 */

public class GetNearbyPlacesData extends AsyncTask<Object, String, String> {

    String googlePlacesData;
    ArrayList<Place> googlePlaces;
//    GoogleMap mMap;
    String url;

    @Override
    protected String doInBackground(Object... objects) {
        googlePlaces = (ArrayList<Place>)objects[0];
//        mMap = (GoogleMap)objects[0];
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
        DataParser dataParser = new DataParser();
        googlePlaces.addAll(dataParser.parsePlaceList(s));
    }



//    private void showNearbyPlaces(List<HashMap<String, String>> nearbyPlacesList) {
//        for (int i = 0;  i < nearbyPlacesList.size(); i++) {
//            MarkerOptions markerOptions = new MarkerOptions();
//            HashMap<String, String> googlePlace = nearbyPlacesList.get(i);
////            System.out.println(googlePlace);
//
//            String placeId = googlePlace.get("id");
//            String placeName = googlePlace.get("place_name");
//            double lat = Double.parseDouble(googlePlace.get("lat"));
//            double lng = Double.parseDouble(googlePlace.get("lng"));
//            String vicinity = googlePlace.get("vicinity");
//            Place place = new Place(placeId, placeName, lat, lng, vicinity);
//
//            LatLng latLng = new LatLng(lat, lng);
//            markerOptions.position(latLng);
//            markerOptions.title(placeName + " : " + vicinity);
//            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
////            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.map_pin_places));
//
////            if (placeName.equals("restaurant")) {
////                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_restaurant_map));
////            } else {
////                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
////            }
//
//            mMap.addMarker(markerOptions);
//        }
//    }
}
