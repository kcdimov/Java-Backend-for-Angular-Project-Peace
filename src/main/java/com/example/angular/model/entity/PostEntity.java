package com.example.angular.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class PostEntity extends BaseEntity {
    private String postName;
    private String description;
    private String imageUrl;

    public PostEntity(String postName, String description, String imageUrl) {
        this.postName = postName;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public PostEntity() {
    }

    @Column(nullable = false)
    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    @Column(nullable = false, columnDefinition = "TEXT")
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
