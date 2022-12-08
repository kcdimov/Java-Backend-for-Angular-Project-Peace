package com.example.angular.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class JobEntity extends BaseEntity {

    private String category;
    private String position;
    private String description;

    public JobEntity(String category, String position, String description) {
        this.category = category;
        this.position = position;
        this.description = description;
    }

    public JobEntity() {
    }

    @Column(nullable = false)
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Column(nullable = false)
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Column(nullable = false, columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
