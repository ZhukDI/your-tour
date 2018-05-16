package com.github.zhukdi.your_tour.models;

/**
 * Created by Dmitry on 4/1/2018.
 */

public class Weather {
    private int id;
    private String mai;
    private String description;
    private String icon;

    public Weather(int id, String mai, String description, String icon) {
        this.id = id;
        this.mai = mai;
        this.description = description;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMai() {
        return mai;
    }

    public void setMai(String mai) {
        this.mai = mai;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
