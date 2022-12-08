package com.example.angular;

import com.example.angular.services.JobService;
import com.example.angular.services.PostService;
import com.example.angular.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {

    private final UserService userService;
    private final PostService postService;
    private final JobService jobService;

    public DBInit(UserService userService, PostService postService, JobService jobService) {
        this.userService = userService;
        this.postService = postService;
        this.jobService = jobService;
    }


    @Override
    public void run(String... args) throws Exception {
        initPosts();
        initUser();
        initJobs();
    }

    private void initUser() {
        this.userService.initUser();
    }

    private void initPosts() {
        this.postService.initPosts();
    }

    private void initJobs() {
        this.jobService.initJobs();
    }
}
