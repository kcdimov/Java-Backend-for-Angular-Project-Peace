package com.example.angular.model.dto;

import com.google.gson.annotations.Expose;

public class JobDto {

    @Expose
    private String category;
    @Expose
    private String position;
    @Expose
    private String description;


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
