package com.microservices.job.job.mapper;

import com.microservices.job.job.Job;
import com.microservices.job.job.dto.JobDTO;
import com.microservices.job.job.external.Company;
import com.microservices.job.job.external.Review;

import java.util.List;

public class Jobmapper {
    public static JobDTO mapjobwithcompany(Company company, Job job,Review review){
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setTittle(job.getTittle());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setMinsalary(job.getMinsalary());
        jobDTO.setMaxsalary(job.getMaxsalary());
        jobDTO.setCountry(job.getCountry());
        jobDTO.setCompany(company);
        jobDTO.setReview(review);
        return jobDTO;
    }
}
