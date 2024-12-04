package com.microservices.reviews.Impl;

import com.microservices.reviews.Review;
import com.microservices.reviews.ReviewRepository;
import com.microservices.reviews.ReviewService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public boolean updateReview(Long reviewId, Review updatedreview) {
        Review review= reviewRepository.findById(reviewId).orElse(null);
        if( review != null){
            review.setTittle(updatedreview.getTittle());
            review.setDescription((updatedreview.getDescription()));
            review.setRating(updatedreview.getRating());
            review.setCompanyId(updatedreview.getCompanyId());
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Review> getReviews(Long companyId) {
       Optional<Review> reviews = reviewRepository.findById(companyId);
       return reviews;
    }

    @Override
    public boolean deleteReviews(Long reviewid) {

        Review review = reviewRepository.findById(reviewid).orElse(null);
        if(review != null){
            reviewRepository.delete(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        return review;
    }

    @Override
    public boolean addreview(Long companyId, Review review) {
        if ((companyId!=null && review != null)){
            review.setCompanyId(companyId);
        reviewRepository.save(review);
        return true ;}
        return false;
    }


}
