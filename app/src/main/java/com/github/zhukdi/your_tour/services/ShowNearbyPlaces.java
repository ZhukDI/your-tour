package com.github.zhukdi.your_tour.services;

import com.github.zhukdi.your_tour.models.Place;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Created by Dmitry on 5/4/2018.
 */

public class ShowNearbyPlaces {


    public static void showNearbyPlaces(GoogleMap mMap, ArrayList<Place> places) {
        for (int i = 0; i < places.size(); i++) {
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(new LatLng(places.get(i).getLat(), places.get(i).getLng()));
            markerOptions.title(places.get(i).getName() + ":" + places.get(i).getVicinity());
            //ToDo: change marker options by type
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
            mMap.addMarker(markerOptions);

        }
    }
}
