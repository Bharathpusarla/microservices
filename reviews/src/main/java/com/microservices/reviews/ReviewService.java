package com.microservices.reviews;

import java.util.Optional;

public interface ReviewService {

    boolean updateReview(Long reviewId,Review updatedreview);
    Optional<Review> getReviews(Long companyId);
    boolean deleteReviews(Long reviewid);
    Review getReview(Long reviewId);
    boolean addreview(Long companyId, Review review);

}
