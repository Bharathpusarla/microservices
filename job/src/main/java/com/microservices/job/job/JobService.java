package com.microservices.job.job;

import com.microservices.job.job.dto.JobDTO;

import java.util.List;

public interface JobService {

    List<JobDTO> getalljobs();

    JobDTO getjobbyid(Long id);

    void create(Job job);

    boolean delete(Long id);

    boolean updatejob(Long id, Job job);
    void getjob();

    void created(Job job1);
}
