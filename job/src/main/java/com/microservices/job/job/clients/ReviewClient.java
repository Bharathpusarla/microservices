package com.microservices.job.job.clients;

import com.microservices.job.job.external.Company;
import com.microservices.job.job.external.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "REVIEWS-SERVICE")
public interface ReviewClient {
    @GetMapping("/reviews")
    Review getReview(@RequestParam("companyId") Long companyId);
}
