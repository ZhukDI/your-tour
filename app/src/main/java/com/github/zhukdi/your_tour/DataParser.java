package com.github.zhukdi.your_tour;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Dmitry on 4/21/2018.
 */

public class DataParser {

    private HashMap<String, String> getDuration(JSONArray googleDirectionJson) {
        HashMap<String, String> googleDirectionsMap = new HashMap<>();
        String duration = "";
        String distance = "";

        Log.d(googleDirectionJson.toString(), "getDuration: json response");
        try {
            duration = googleDirectionJson.getJSONObject(0).getJSONObject("duration").getString("text");
            distance = googleDirectionJson.getJSONObject(0).getJSONObject("distance").getString("text");

            googleDirectionsMap.put("duration", duration);
            googleDirectionsMap.put("distance", distance);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return googleDirectionsMap;
    }

    private HashMap<String, String> getPlace(JSONObject googlePlaceJson) {
        //ToDo: change to return Place.class
        HashMap<String, String> googlePlaceMap = new HashMap<>();
        String placeName = "-NA-";
        String vicinity = "-NA-";
        String latitude = "";
        String longitude = "";
        String reference = "";

        try {
            if (!googlePlaceJson.isNull("name")) {
                placeName = googlePlaceJson.getString("name");
            }
            if (!googlePlaceJson.isNull("vicinity")) {
                vicinity = googlePlaceJson.getString("vicinity");
            }
            latitude = googlePlaceJson.getJSONObject("geometry").getJSONObject("location").getString("lat");
            longitude = googlePlaceJson.getJSONObject("geometry").getJSONObject("location").getString("lng");
            reference = googlePlaceJson.getString("reference");

            googlePlaceMap.put("place_name", placeName);
            googlePlaceMap.put("vicinity", vicinity);
            googlePlaceMap.put("lat", latitude);
            googlePlaceMap.put("lng", longitude);
            googlePlaceMap.put("reference", reference);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return googlePlaceMap;
    }

    private List<HashMap<String, String>> getPlaces(JSONArray jsonArray) {
        List<HashMap<String, String>> placesList = new ArrayList<>();
        HashMap<String, String> placeMap = null;
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                placeMap = getPlace((JSONObject) jsonArray.get(i));
                placesList.add(placeMap);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return placesList;
    }

    private PlaceDetails getPlaceDetails(JSONObject jsonObject) {
//        PlaceDetails placeDetails = new PlaceDetails();
        String formattedAddress = "";
        String formattedPhoneNumber = "";
        String icon = "";
        String name = "";
        double rating = 0;
        String website = "";
        try {
            if (!jsonObject.isNull("formatted_address")) {
                formattedAddress = jsonObject.getString("formatted_address");
            }
            if (!jsonObject.isNull("formatted_phone_number")) {
                formattedPhoneNumber = jsonObject.getString("formatted_phone_number");
            }
            if (!jsonObject.isNull("icon")) {
                icon = jsonObject.getString("icon");
            }
            if (!jsonObject.isNull("name")) {
                name = jsonObject.getString("name");
            }
            if (!jsonObject.isNull("rating")) {
                rating = jsonObject.getDouble("rating");
            }
            if (!jsonObject.isNull("website")) {
                website = jsonObject.getString("website");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new PlaceDetails(formattedAddress, formattedPhoneNumber, icon, name, rating, website);
    }

    public List<HashMap<String, String>> parse(String jsonData) {
        JSONArray jsonArray = null;
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            jsonArray = jsonObject.getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return getPlaces(jsonArray);
    }

    public HashMap<String, String> parseDirections(String jsonData) {
        JSONArray jsonArray = null;
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            jsonArray = jsonObject.getJSONArray("routes").getJSONObject(0).getJSONArray("legs");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return getDuration(jsonArray);
    }

    public HashMap<String, String> parsePlaceDetailsData(String jsonData) {
        JSONObject jsonObject = null;
        JSONObject jsonPlaceDetails = null;
        try {
            jsonObject = new JSONObject(jsonData);
            jsonPlaceDetails = jsonObject.getJSONObject("result");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(jsonPlaceDetails);
        System.out.println("_______________________________________");
        System.out.println(getPlaceDetails(jsonPlaceDetails));
        System.out.println("_______________________________________");
        return null;
    }


}
