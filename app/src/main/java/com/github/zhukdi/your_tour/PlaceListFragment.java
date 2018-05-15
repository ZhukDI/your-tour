package com.github.zhukdi.your_tour;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
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

import java.util.ArrayList;
import java.util.List;

import static com.github.zhukdi.your_tour.settings.AppSettings.GOOGLE_PLACES_API_KEY;
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
        loadRecyclerViewData();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Nearby places");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

                        DataParser dataParser = new DataParser();
                        placeList = dataParser.parsePlaceList(s);

                        // temp
                        for (int i = 0; i < placeList.size(); i++) {
                            System.out.println(placeList.get(i));
                        }

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

    private String getNearbyPlaceUrl(double latitude, double longitute, String nearbyPlace) {
        StringBuilder googlePlaceUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlaceUrl.append("location=" + latitude + "," + longitute);
        googlePlaceUrl.append("&radius=" + GOOGLE_PLACES_LOCATION_RADIUS);
        googlePlaceUrl.append("&type=" + nearbyPlace);
        googlePlaceUrl.append("&sensor=true");
        googlePlaceUrl.append("&key=" + GOOGLE_PLACES_API_KEY);
        return googlePlaceUrl.toString();
    }

}
