package com.example.angular.controller;


import com.example.angular.model.entity.PostEntity;
import com.example.angular.services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/all")
    private List<PostEntity> getAllPosts() {
        return this.postService.getAllPosts();
    }

    @GetMapping("/{id}")
    private PostEntity getById(@PathVariable Long id) {
        return this.postService.getById(id);
    }

    @PostMapping("/addPost")
    private void addAccommodation (@RequestBody String accommodation) {

        this.postService.addPost(accommodation);
    }
}
