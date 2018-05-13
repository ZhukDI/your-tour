package com.github.zhukdi.your_tour.model;

/**
 * Created by Dmitry on 5/13/2018.
 */

public class PlacePhoto {

    private int height;
    private int width;
    private String photoReference;

    public PlacePhoto() {

    }

    public PlacePhoto(int height, int width, String photoReference) {
        this.height = height;
        this.width = width;
        this.photoReference = photoReference;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getPhotoReference() {
        return photoReference;
    }

    public void setPhotoReference(String photoReference) {
        this.photoReference = photoReference;
    }

    @Override
    public String toString() {
        return "PlacePhoto{" +
                "height=" + height +
                ", width=" + width +
                ", photoReference='" + photoReference + '\'' +
                '}';
    }
}
