package com.example.ahmad_afaq_s1625481.mpdcoursework_aa.models;



import java.util.Arrays;
import java.util.Date;

public class Roadwork {
    private String title;
    private String desc;
    private String[] coords;
    private Date date;
    private String type;


    public Roadwork(String title, String desc, String[] coords, Date date, String type) {
        this.title = title;
        this.desc = desc;
        this.coords = coords;
        this.date = date;
        this.type = type;
    }

    public Roadwork(){}

    @Override
    public String toString() {
        return "Roadwork{" +
                "title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", coords=" + Arrays.toString(coords) +
                ", date='" + date + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String[] getCoords() {
        return coords;
    }

    public void setCoords(String[] coords) {
        this.coords = coords;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
