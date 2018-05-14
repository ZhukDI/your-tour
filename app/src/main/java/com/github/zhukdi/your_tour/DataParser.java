package com.github.zhukdi.your_tour;

import android.util.Log;

import com.github.zhukdi.your_tour.model.Place;
import com.github.zhukdi.your_tour.model.PlacePhoto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

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

    private Place getPlace(JSONObject googlePlaceJson) {
        Place place = new Place();
        String id = "";
        String placeName = "-NA-";
        String vicinity = "-NA-";
        Double latitude = null;
        Double longitude = null;
        Double rating = null;
        ArrayList<PlacePhoto> photos = null;

        try {
            id = googlePlaceJson.getString("id");
            if (!googlePlaceJson.isNull("name")) {
                placeName = googlePlaceJson.getString("name");
            }
            if (!googlePlaceJson.isNull("vicinity")) {
                vicinity = googlePlaceJson.getString("vicinity");
            }
            latitude = googlePlaceJson.getJSONObject("geometry").getJSONObject("location").getDouble("lat");
            longitude = googlePlaceJson.getJSONObject("geometry").getJSONObject("location").getDouble("lng");
            rating = googlePlaceJson.getDouble("rating");
            photos = getPlacePhotos(googlePlaceJson.getJSONArray("photos"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        place.setId(id);
        place.setName(placeName);
        place.setLat(latitude);
        place.setLng(longitude);
        place.setVicinity(vicinity);
        place.setRating(rating);
        place.setPhotos(photos);
        return place;
    }

    private ArrayList<PlacePhoto> getPlacePhotos(JSONArray jsonArray) {
        ArrayList<PlacePhoto> placePhotos = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                placePhotos.add(getPlacePhoto((JSONObject) jsonArray.get(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return placePhotos;
    }

    private PlacePhoto getPlacePhoto(JSONObject jsonObject) {
        PlacePhoto placePhoto = new PlacePhoto();
        try {
            placePhoto.setHeight(jsonObject.getInt("height"));
            placePhoto.setWidth(jsonObject.getInt("width"));
            placePhoto.setPhotoReference(jsonObject.getString("photo_reference"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return placePhoto;
    }

    private ArrayList<Place> getPlaces(JSONArray jsonArray) {
        ArrayList<Place> placesList = new ArrayList<>();
        Place place = null;
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                place = getPlace((JSONObject) jsonArray.get(i));
                placesList.add(place);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return placesList;
    }

    private PlaceDetails getPlaceDetails(JSONObject jsonObject) {
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

    public ArrayList<Place> parsePlaceList(String jsonData) {
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

    //Todo: replace HashMap to Place.class or delete
    public HashMap<String, String> parsePlaceDetailsData(String jsonData) {
        JSONObject jsonObject = null;
        JSONObject jsonPlaceDetails = null;
        try {
            jsonObject = new JSONObject(jsonData);
            jsonPlaceDetails = jsonObject.getJSONObject("result");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // temp
        System.out.println(jsonPlaceDetails);
        System.out.println("_______________________________________");
        System.out.println(getPlaceDetails(jsonPlaceDetails));
        System.out.println("_______________________________________");
        return null;
    }


}
