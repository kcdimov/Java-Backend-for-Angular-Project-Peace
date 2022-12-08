package com.example.angular.model.dto;

import com.google.gson.annotations.Expose;

public class PostDto {

    @Expose
    private String postName;
    @Expose
    private String description;
    @Expose
    private String imageUrl;


    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
