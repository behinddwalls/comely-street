package com.comelystreet.dao.mongodb.model;

public class ReviewDetail {
    private String reviewComment;
    private int rating;

    public String getReviewComment() {
        return reviewComment;
    }

    public void setReviewComment(String reviewComment) {
        this.reviewComment = reviewComment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "ReviewDetail [reviewComment=" + reviewComment + ", rating=" + rating + "]";
    }
}
