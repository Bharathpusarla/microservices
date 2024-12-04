package com.microservices.reviews;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @GetMapping()
    public ResponseEntity<Optional<Review>> getReviews(@RequestParam Long companyId ){
        return new ResponseEntity<>(reviewService.getReviews(companyId), HttpStatus.OK);
    }
    @GetMapping("/{reviewId}")
    public  ResponseEntity<Review> getReview(@PathVariable Long reviewId){
            Review review = reviewService.getReview(reviewId);
       if(review != null) {
           return new ResponseEntity<>(review, HttpStatus.OK);
       }
       return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public  ResponseEntity<String> createReview(@RequestParam Long companyId,@RequestBody Review review) {
        boolean addrveiew = reviewService.addreview(companyId, review);
        if (addrveiew){
            return new ResponseEntity<>("Review posted", HttpStatus.CREATED);
    }
        return new ResponseEntity<>("review not added",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId){
        boolean review = reviewService.deleteReviews(reviewId);
        if(review) {
            return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("review not found",HttpStatus.NOT_FOUND);
    }
    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId,@RequestBody Review review) {
        boolean updated = reviewService.updateReview(reviewId, review);
        if (updated){
            return new ResponseEntity<>("review updated", HttpStatus.OK);}
        return new ResponseEntity<>("Review not found", HttpStatus.OK);
    }

}
