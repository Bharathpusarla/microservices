package com.microservices.job.job.Impl;
import com.microservices.job.job.Job;
import com.microservices.job.job.JobRepository;
import com.microservices.job.job.JobService;
import com.microservices.job.job.clients.CompanyClient;
import com.microservices.job.job.clients.ReviewClient;
import com.microservices.job.job.dto.JobDTO;
import com.microservices.job.job.external.Company;
import com.microservices.job.job.external.Review;
import com.microservices.job.job.mapper.Jobmapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceimpl implements JobService {
    private JobRepository jobRepository;

    @Autowired
    RestTemplate restTemplate;
    CompanyClient companyClient;
    ReviewClient reviewClient;
    public JobServiceimpl(JobRepository jobRepository, CompanyClient companyClient, ReviewClient reviewClient) {
        this.jobRepository = jobRepository;
        this.companyClient = companyClient;
        this.reviewClient = reviewClient;
    }

    @Override
    @CircuitBreaker(name="companyBreaker",fallbackMethod = "companyBreakerFallback")
    public List<JobDTO> getalljobs() {

        List<Job> jobs= jobRepository.findAll();
        List<JobDTO> jobDTOS = new ArrayList<>();

        return jobs.stream().map(this::convertToDto)
                .collect(Collectors.toList());
    }
    public List<JobDTO> companyBreakerFallback(Throwable throwable) {
        System.out.println("Fallback triggered due to: " + throwable.getMessage());
        List<JobDTO> fallbackList = new ArrayList<>();
        return fallbackList;
    }



    private JobDTO convertToDto(Job job){

      // String url = "http://COMPANY-SERVICE:8081/companies/" + job.getCompanyid()
        // Company company = restTemplate.getForObject(url, Company.class);
//        ResponseEntity<Review> reviewResponse =restTemplate.exchange("http://REVIEWS-SERVICE:8083/reviews?companyId="+job.getCompanyid(),
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<Review>() {
//        });
//        Review reviews = reviewResponse.getBody();
        Company company = companyClient.getCompany(job.getCompanyid());
        Review review = reviewClient.getReview(job.getCompanyid());
       JobDTO jobDTO = Jobmapper.mapjobwithcompany(company,job,review);
        return jobDTO;
    }

    @Override
    public JobDTO getjobbyid(Long id) {
        Job job= jobRepository.findById(id).orElse(null);
        return convertToDto(job);
    }

    @Override
    public void create(Job job) {
        jobRepository.save(job);
    }

    @Override
    public boolean delete(Long id) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            jobRepository.deleteById(id);
            return true;
        }
        return false;
    }
    @Override
    public boolean updatejob(Long id, Job job) {
       Optional<Job> existingjobOptional = jobRepository.findById(id);
       if(existingjobOptional.isPresent()){
           Job existingjob = existingjobOptional.get();
            existingjob.setTittle(job.getTittle());
            existingjob.setDescription(job.getDescription());
            existingjob.setMaxsalary(job.getMaxsalary());
            existingjob.setMinsalary(job.getMinsalary());
            existingjob.setLocation(job.getLocation());
            existingjob.setCountry(job.getCountry());
            jobRepository.save(existingjob);
            return true;
        }

        return false;
    }

    @Override
    public void getjob() {

    }

    @Override
    public void created(Job job1) {

        job1.setCountry("Australia");
        jobRepository.save(job1);
    }

}
