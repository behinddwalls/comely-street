package com.comelystreet.mongodb.types;

import com.comelystreet.dao.mongodb.model.BookingServicesDetail;

public class BookingResponseDataModel {
    private BookingServicesDetail bookingServicesDataModel;
    private String status;
    private String[] errors;

    public BookingServicesDetail getBookingServicesDataModel() {
        return bookingServicesDataModel;
    }

    public void setBookingServicesDataModel(BookingServicesDetail bookingServicesDataModel) {
        this.bookingServicesDataModel = bookingServicesDataModel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String[] getErrors() {
        return errors;
    }

    public void setErrors(String[] errors) {
        this.errors = errors;
    }

    public BookingResponseDataModel(BookingServicesDetail bookingServicesDataModel, String status, String[] errors) {
        super();
        this.bookingServicesDataModel = bookingServicesDataModel;
        this.status = status;
        this.errors = errors;
    }
}
