package com.example.angular.services;

import com.example.angular.model.entity.PostEntity;

import java.util.List;

public interface PostService {

    void initPosts();

    void addPost(String post);

    List<PostEntity> getAllPosts();

    PostEntity getById(Long id);

}
