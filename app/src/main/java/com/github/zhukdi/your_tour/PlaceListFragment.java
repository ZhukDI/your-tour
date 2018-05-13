package com.github.zhukdi.your_tour;


import android.app.ProgressDialog;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.zhukdi.your_tour.adapter.PlaceAdapter;
import com.github.zhukdi.your_tour.model.Place;
import com.github.zhukdi.your_tour.settings.AppSettings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.github.zhukdi.your_tour.settings.AppSettings.GOOGLE_PLACES_LOCATION_RADIUS;
import static com.github.zhukdi.your_tour.settings.AppSettings.currentLocation;


public class PlaceListFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<Place> placeList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_place_list, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.place_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        placeList = new ArrayList<>();
//        loadRecyclerViewData();

//        for (int i = 0; i < 5; i++) {
//            Place place = new Place("name" + (i+1), "temp vicinity");
//            placeList.add(place);
//        }
//        Object dataTransfer[] = new Object[2];
//        GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
//        String restaurant = "restaurant";
////        String url = getNearbyPlaceUrl(currentLocation.getLatitude(), currentLocation.getLongitude(), restaurant);
//        String url = getNearbyPlaceUrl(53.888674, 27.442427, restaurant);
//        dataTransfer[0] = placeList;
//        dataTransfer[1] = url;
//        getNearbyPlacesData.execute(dataTransfer);
//        placeList = getNearbyPlacesData.getGooglePlaces();
//        System.out.println("************************");
////        System.out.println(placeList.size());
//        adapter = new PlaceAdapter(placeList, getContext());
//        recyclerView.setAdapter(adapter);

        loadRecyclerViewData();

        return view;
    }

    private void loadRecyclerViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        String nearbyPlaceType = "restaurant"; //ToDo: make it dynamic
        String url = getNearbyPlaceUrl(currentLocation.getLatitude(), currentLocation.getLongitude(), nearbyPlaceType);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        progressDialog.dismiss();
                        List<HashMap<String, String>> nearbyPlaceListJson = null;
                        DataParser dataParser = new DataParser();
                        nearbyPlaceListJson = dataParser.parse(s);

                        placeList = getGooglePlaces(nearbyPlaceListJson);

                        adapter = new PlaceAdapter(placeList, getContext());
                        recyclerView.setAdapter(adapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    private List<Place> getGooglePlaces(List<HashMap<String, String>> nearbyPlaceList) {
        List<Place> googlePlaces = new ArrayList<>();
        for (int i = 0;  i < nearbyPlaceList.size(); i++) {
            HashMap<String, String> googlePlace = nearbyPlaceList.get(i);
            String placeId = googlePlace.get("id");
            String placeName = googlePlace.get("place_name");
            double lat = Double.parseDouble(googlePlace.get("lat"));
            double lng = Double.parseDouble(googlePlace.get("lng"));
            String vicinity = googlePlace.get("vicinity");
            ArrayList photos = new ArrayList();
            System.out.println("_________________________");
            System.out.println(googlePlace);
            googlePlaces.add(new Place(placeId, placeName, lat, lng, vicinity, photos));
        }
        return googlePlaces;
    }

    private String getNearbyPlaceUrl(double latitude, double longitute, String nearbyPlace) {
        StringBuilder googlePlaceUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlaceUrl.append("location=" + latitude + "," + longitute);
        googlePlaceUrl.append("&radius=" + GOOGLE_PLACES_LOCATION_RADIUS);
        googlePlaceUrl.append("&type=" + nearbyPlace);
        googlePlaceUrl.append("&sensor=true");
        googlePlaceUrl.append("&key=AIzaSyAOH3GNnI6R3RwJqygqf3ciMFHp6TismDA");
        return googlePlaceUrl.toString();
    }

}
