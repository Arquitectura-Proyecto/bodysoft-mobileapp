package com.example.myapplication.Model.Entities;

import com.example.apollographqlandroid.GetRoutinesByIdOwnerQuery;

public class RoutineEntity  {
    private  int id;
    private int idOwner;
    private float raiting;
    private int numRaitings;
    private float price;
    private String name;
    private String description;
    private String link_preview;
    private int idtype;
    public RoutineEntity(){}

    public RoutineEntity(int id, int idOwner, float raiting, int numRaitings, float price, String name, String description, String link_preview, int idtype) {
        this.id = id;
        this.idOwner = idOwner;
        this.raiting = raiting;
        this.numRaitings = numRaitings;
        this.price = price;
        this.name = name;
        this.description = description;
        this.link_preview = link_preview;
        this.idtype = idtype;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(int idOwner) {
        this.idOwner = idOwner;
    }

    public float getRaiting() {
        return raiting;
    }

    public void setRaiting(float raiting) {
        this.raiting = raiting;
    }

    public int getNumRaitings() {
        return numRaitings;
    }

    public void setNumRaitings(int numRaitings) {
        this.numRaitings = numRaitings;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink_preview() {
        return link_preview;
    }

    public void setLink_preview(String link_preview) {
        this.link_preview = link_preview;
    }

    public int getIdtype() {
        return idtype;
    }

    public void setIdtype(int idtype) {
        this.idtype = idtype;
    }
}
