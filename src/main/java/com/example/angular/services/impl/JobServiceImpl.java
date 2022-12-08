package com.example.angular.services.impl;

import com.example.angular.model.dto.JobDto;
import com.example.angular.model.entity.JobEntity;
import com.example.angular.repository.JobRepository;
import com.example.angular.services.JobService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;


@CrossOrigin
@Service
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;

    public JobServiceImpl(JobRepository jobRepository, ModelMapper modelMapper, Gson gson) {
        this.jobRepository = jobRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public void initJobs() {
        if (this.jobRepository.count() != 0 ) {
            return;
        }

        this.jobRepository.save(new JobEntity("Computing and ICT", "App developer",
                "You might focus specifically on apps for phones, tablets, computers or gaming devices.\n" +
                        "It's also likely you'd work within a specific field or with specific systems\n" +
                        "such as Windows, iOS or Android) to create different types of apps. "));

    }

    @Override
    public void addJob(String job) {
        JobDto jobDto = this.gson.fromJson(job, JobDto.class);
        JobEntity jobEntity = this.modelMapper.map(jobDto, JobEntity.class);

        this.jobRepository.save(jobEntity);

    }

    @Override
    public List<JobEntity> getAllJobs() {
        return this.jobRepository.findAll();
    }

    @Override
    public JobEntity getById(Long id) {
        return this.jobRepository.findById(id).get();
    }
}
