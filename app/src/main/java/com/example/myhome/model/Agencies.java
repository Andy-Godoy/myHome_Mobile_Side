package com.example.myhome.model;


public class Agencies {

    private String agencyEmail;
    private String  agencyId;
    private String  userId;
    private String agencyName;
    private Float agencyRating;



    public Float getAgencyRating() {
        return agencyRating;
    }

    public void setAgencyRating(Float agencyRating) {
        this.agencyRating = agencyRating;
    }

    public String getAgencyEmail() { return agencyEmail; }

    public String  getAgencyId() { return agencyId; }

    public String  getUserId() { return userId; }

    public String getAgencyName() { return agencyName; }

    public void setAgencyEmail(String agencyEmail) {
        this.agencyEmail = agencyEmail;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

}