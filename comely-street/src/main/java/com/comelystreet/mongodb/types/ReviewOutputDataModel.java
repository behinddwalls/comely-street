package com.comelystreet.mongodb.types;

public class ReviewOutputDataModel extends ReviewInputDataModel {
    private String customerName;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "ReviewOutputDataModel [customerName=" + customerName + ", getBookingId()=" + getBookingId()
                + ", getReviewComment()=" + getReviewComment() + ", getRating()=" + getRating() + ", toString()="
                + super.toString() + ", getStoreId()=" + getStoreId() + ", getClass()=" + getClass() + ", hashCode()="
                + hashCode() + "]";
    }

}
