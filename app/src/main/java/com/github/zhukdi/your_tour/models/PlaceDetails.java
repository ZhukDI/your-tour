package com.github.zhukdi.your_tour.models;

/**
 * Created by Dmitry on 5/1/2018.
 */

public class PlaceDetails {
    private String formattedAddress;
    private String formattedPhoneNumber;
    private String icon;
    private String name;
    private double rating;
    private String website;

    public PlaceDetails(String formattedAddress, String formattedPhoneNumber, String icon, String name, double rating, String website) {
        this.formattedAddress = formattedAddress;
        this.formattedPhoneNumber = formattedPhoneNumber;
        this.icon = icon;
        this.name = name;
        this.rating = rating;
        this.website = website;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public String getFormattedPhoneNumber() {
        return formattedPhoneNumber;
    }

    public void setFormattedPhoneNumber(String formattedPhoneNumber) {
        this.formattedPhoneNumber = formattedPhoneNumber;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String toString() {
        return "PlaceDetails{" +
                "formattedAddress='" + formattedAddress + '\'' +
                ", formattedPhoneNumber='" + formattedPhoneNumber + '\'' +
                ", icon='" + icon + '\'' +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", website='" + website + '\'' +
                '}';
    }
}
