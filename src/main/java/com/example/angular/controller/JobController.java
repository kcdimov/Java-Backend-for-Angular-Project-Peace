package com.example.angular.controller;


import com.example.angular.model.entity.JobEntity;
import com.example.angular.services.JobService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/all")
    private List<JobEntity> getAllJobs() {
        return this.jobService.getAllJobs();
    }

    @GetMapping("/{id}")
    private JobEntity getById (@PathVariable Long id) {
        return this.jobService.getById(id);
    }

    @PostMapping("/addJob")
    private void AddJob (@RequestBody String job) {
        this.jobService.addJob(job);
    }
}
