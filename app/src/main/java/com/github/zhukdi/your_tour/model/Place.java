package com.github.zhukdi.your_tour.model;

import java.util.ArrayList;

/**
 * Created by Dmitry on 5/1/2018.
 */

public class Place {
    private String id;
    private String name;
    private double lat;
    private double lng;
    private String vicinity;
    private ArrayList photos;

    public Place(String name, String vicinity) {
        this.name = name;
        this.vicinity = vicinity;
    }

    public Place(String id, String name, double lat, double lng, String vicinity, ArrayList photos) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.vicinity = vicinity;
        this.photos = photos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                ", vicinity='" + vicinity + '\'' +
                '}';
    }
}
