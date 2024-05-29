package com.jobappms.reviewms.review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);
    boolean addReview(Long companyId, Review review);
    boolean updateReview(Long reviewId, Review review);
    boolean deleteReview(Long reviewId);
    Review getReview(Long reviewId);
}
