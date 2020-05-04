package com.example.myapplication.ui.Profile;

public class EntityDegree {

    private String degree_title;
    private String year;
    private String institution;
    private int degree_image;


    public EntityDegree(String degree_name, String year, String institution, int degree_image) {
        this.degree_title = degree_name;
        this.year = year;
        this.institution = institution;
        this.degree_image = degree_image;
    }

    public String getDegree_title() {
        return degree_title;
    }

    public String getYear() {
        return year;
    }

    public String getInstitution() {
        return institution;
    }

    public int getDegree_image() {
        return degree_image;
    }
}
