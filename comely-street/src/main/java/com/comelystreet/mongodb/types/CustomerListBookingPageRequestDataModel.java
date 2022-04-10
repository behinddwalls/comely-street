package com.comelystreet.mongodb.types;

public class CustomerListBookingPageRequestDataModel {
    private Pagination pagination;
    private String customerId;
    private BookingTimelineType bookingTimelineTypeEnum;

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public BookingTimelineType getBookingTimelineTypeEnum() {
        return bookingTimelineTypeEnum;
    }

    public void setBookingTimelineTypeEnum(BookingTimelineType bookingTimelineType) {
        this.bookingTimelineTypeEnum = bookingTimelineType;
    }

    @Override
    public String toString() {
        return "CustomerListBookingPageRequestDataModel [pagination=" + pagination + ", customerId=" + customerId
                + ", bookingTimelineTypeEnum=" + bookingTimelineTypeEnum + "]";
    }
}
