package com.example.administrator.entity;

import java.util.Date;

/**
 * Created by Administrator on 2018/1/7 0007.
 */

public class FutureInfo {
    private Date date;
    private String night;
    private String temperature;
    private String week;
    private String wind;

    public FutureInfo() {
    }

    public FutureInfo(Date date, String night, String temperature, String week, String wind) {
        this.date = date;
        this.night = night;
        this.temperature = temperature;
        this.week = week;
        this.wind = wind;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNight() {
        return night;
    }

    public void setNight(String night) {
        this.night = night;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }
}
