package com.example.angular.services;


import com.example.angular.model.dto.EditProfileDto;
import com.example.angular.model.dto.UserDto;
import com.example.angular.model.entity.JobEntity;
import com.example.angular.model.entity.PostEntity;
import com.example.angular.model.entity.UserEntity;

import java.util.List;

public interface UserService {
    List<UserEntity> getAllUsers();
    void registerUser(UserDto userDto);
    void addPostToUser(Long postId, String userEmail);
    void addJobToUser(Long jobId, String userEmail);

    List<PostEntity> getMyPosts(String email);
    List<JobEntity> getMyJobs(String email);

    void login (UserDto userDto);

    UserEntity getCurrentUser();

    void logout();

    void initUser();

    void editUserInfo(EditProfileDto editProfileInfo);
}
