package com.example.ahmad_afaq_s1625481.mpdcoursework_aa.models;




import java.util.Arrays;
import java.util.Date;

public class Incident {

    private String title;
    private String desc;
    private String incidentLink;
    private Date date;
    private String[] coors;

    public Incident(){

    }

    public Incident(String title, String desc, String incidentLink, Date date, String[] coors) {
        this.title = title;
        this.desc = desc;
        this.incidentLink = incidentLink;
        this.date = date;
        this.coors = coors;
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

    public String getIncidentLink() {
        return incidentLink;
    }

    public void setIncidentLink(String incidentLink) {
        this.incidentLink = incidentLink;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCoors(String[] coors) {
        this.coors = coors;
    }

    @Override
    public String toString() {
        return "Incident{" +
                "title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", incidentLink='" + incidentLink + '\'' +
                ", date=" + date +
                ", coors=" + Arrays.toString(coors) +
                '}';
    }
}
