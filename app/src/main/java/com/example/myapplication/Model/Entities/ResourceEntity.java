package com.example.myapplication.Model.Entities;

public class ResourceEntity {
    private int idRoutine;
    private String link;
    private String title;
    private int position;
    private int IdType;
    private String description;

    public ResourceEntity() {
    }

    public ResourceEntity(int idRoutine, String link, String title, int position, int idType, String description) {
        this.idRoutine = idRoutine;
        this.link = link;
        this.title = title;
        this.position = position;
        IdType = idType;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdRoutine() {
        return idRoutine;
    }

    public void setIdRoutine(int idRoutine) {
        this.idRoutine = idRoutine;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getIdType() {
        return IdType;
    }

    public void setIdType(int idType) {
        IdType = idType;
    }
}
