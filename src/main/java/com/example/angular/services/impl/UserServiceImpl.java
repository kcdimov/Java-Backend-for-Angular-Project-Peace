package com.example.angular.services.impl;

import com.example.angular.model.CurrentUser;
import com.example.angular.model.dto.EditProfileDto;
import com.example.angular.model.dto.UserDto;
import com.example.angular.model.entity.JobEntity;
import com.example.angular.model.entity.PostEntity;
import com.example.angular.model.entity.UserEntity;
import com.example.angular.repository.UserRepository;
import com.example.angular.services.JobService;
import com.example.angular.services.PostService;
import com.example.angular.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final CurrentUser currentUser;
    private final PostService postService;
    private final JobService jobService;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository,
                           CurrentUser currentUser, PostService postService, JobService jobService) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.currentUser = currentUser;

        this.postService = postService;
        this.jobService = jobService;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public void registerUser(UserDto userDto) {
        if (this.userRepository.findByEmail(userDto.getEmail()) != null) {
            throw new IllegalArgumentException("Already have user with this email");
        }
        UserEntity mapUser = this.modelMapper.map(userDto, UserEntity.class);
        mapUser.setUserRole("user");
        this.userRepository.save(mapUser);
    }

    @Override
    public void addPostToUser(Long postId, String userEmail) {
        UserEntity user = this.userRepository.findByEmail(userEmail);
        for (int i = 0; i < user.getPosts().size(); i++) {
            if (user.getPosts().get(i).getId().equals(postId)) {
                return;
            }
        }
        PostEntity postEntity = this.postService.getById(postId);
        user.getPosts().add(postEntity);
        this.userRepository.save(user);
    }

    @Override
    public void addJobToUser(Long jobId, String userEmail) {

        UserEntity user = this.userRepository.findByEmail(userEmail);
        for (int i = 0; i < user.getJobs().size(); i++) {
            if (user.getJobs().get(i).getId().equals(jobId)) {
                return;
            }
        }
        JobEntity jobEntity = this.jobService.getById(jobId);
        user.getJobs().add(jobEntity);
        this.userRepository.save(user);
    }

    @Override
    public List<PostEntity> getMyPosts(String email) {
        UserEntity userEntity = this.userRepository.findByEmail(email);

        return userEntity.getPosts();
    }

    @Override
    public List<JobEntity> getMyJobs(String email) {
        UserEntity userEntity = this.userRepository.findByEmail(email);
        return userEntity.getJobs();
    }

    @Override
    public void login(UserDto userDto) {
        UserEntity userMap = this.modelMapper.map(userDto, UserEntity.class);
        UserEntity userByEmail = this.userRepository.findByEmail(userMap.getEmail());

        if (userByEmail == null || !userMap.getPassword().equals(userByEmail.getPassword())) {
            throw new IllegalArgumentException("User doesn't exist!");
        }

        this.currentUser.setEmail(userByEmail.getEmail());
    }

    @Override
    public UserEntity getCurrentUser() {

        return this.userRepository.findByEmail(this.currentUser.getEmail());
    }

    @Override
    public void logout() {
        this.currentUser.setEmail(null);
    }

    @Override
    public void initUser() {
        if (this.userRepository.count() != 0) {
            return;
        }

        this.userRepository.save(new UserEntity("Jack", "Reacher",
                "jack@gmail.com", "12345", "admin"));

    }

    @Override
    public void editUserInfo(EditProfileDto editProfileInfo) {
        UserEntity userByEmail = this.userRepository.findByEmail(this.currentUser.getEmail());

        if (!userByEmail.getPassword().equals(editProfileInfo.getOldPassword())) {
            throw new IllegalArgumentException("Incorrect password! Passwords are not the same.");
        }

        userByEmail.setFirstName(editProfileInfo.getFirstName());
        userByEmail.setLastName(editProfileInfo.getLastName());
        userByEmail.setPassword(editProfileInfo.getNewPassword());
        this.userRepository.save(userByEmail);
    }
}
