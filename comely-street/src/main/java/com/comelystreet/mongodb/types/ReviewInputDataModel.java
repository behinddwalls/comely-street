package com.comelystreet.mongodb.types;

public class ReviewInputDataModel {
    private String bookingId;
    private String storeId;
    private String reviewComment;
    private int rating;

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

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
        return "ReviewInputDataModel [bookingId=" + bookingId + ", storeId=" + storeId + ", reviewComment="
                + reviewComment + ", rating=" + rating + "]";
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
}
