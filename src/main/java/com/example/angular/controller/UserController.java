package com.example.angular.controller;


import com.example.angular.model.dto.EditProfileDto;
import com.example.angular.model.dto.UserDto;
import com.example.angular.model.entity.JobEntity;
import com.example.angular.model.entity.PostEntity;
import com.example.angular.model.entity.UserEntity;
import com.example.angular.services.UserService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final ModelMapper modelMapper;
    private final UserService userService;
    
    private Gson gson;


    public UserController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @PostMapping("/users/login")
    private UserEntity loginUser(@RequestBody String user) {
        UserDto userDto = this.gson.fromJson(user, UserDto.class);
        this.userService.login(userDto);

        return this.userService.getCurrentUser();
    }

    @PostMapping("/users/register")
    private void registerUser(@RequestBody String user) {
        UserDto userDto = this.gson.fromJson(user, UserDto.class);
        this.userService.registerUser(userDto);
    }

    @PostMapping("users/addPost/{id}")
    private void addPostToUser(@PathVariable Long id, @RequestBody String userEmail) {
        this.userService.addPostToUser(id, userEmail);
    }

    @RequestMapping("/users/getMyPosts/{email}")
    private List<PostEntity> getMyPosts (@PathVariable String email) {
        return this.userService.getMyPosts(email);
    }

    @RequestMapping("/users/getMyJobs/{email}")
    private List<JobEntity> getMyJobs(@PathVariable String email) {
        return this.userService.getMyJobs(email);
    }

    @PostMapping("users/addJob{id}")
    private void addJobToUser(@PathVariable Long id, @RequestBody String userEmail) {
        this.userService.addJobToUser(id, userEmail);
    }

    @RequestMapping("/users/profile")
    private UserEntity profile() {
        return this.userService.getCurrentUser();
    }

    @PostMapping("/users/logout")
    private UserEntity logout() {
        this.userService.logout();
        return null;
    }


    @PostMapping("/users/editProfile")
    private UserEntity editProfile(@RequestBody String user) {
        EditProfileDto editProfileDto = this.gson.fromJson(user, EditProfileDto.class);
        this.userService.editUserInfo(editProfileDto);
        return this.userService.getCurrentUser();
    }

}
