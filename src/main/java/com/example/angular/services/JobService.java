package com.example.angular.services;

import com.example.angular.model.entity.JobEntity;

import java.util.List;

public interface JobService {

    void initJobs();

    void addJob(String job);

    List<JobEntity> getAllJobs();

    JobEntity getById(Long id);
}
