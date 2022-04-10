package com.comelystreet.mongodb.types;

import java.util.List;

public class BookingServicesInputDataModel {
    private String storeId;
    private List<String> selectedServices;
    private String bookingDate;
    private long bookingTimeSlot;

    private String name;
    private String mobileNumber;
    private String email;

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public List<String> getSelectedServices() {
        return selectedServices;
    }

    public void setSelectedServices(List<String> selectedServices) {
        this.selectedServices = selectedServices;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getBookingTimeSlot() {
        return bookingTimeSlot;
    }

    public void setBookingTimeSlot(long bookingTimeSlot) {
        this.bookingTimeSlot = bookingTimeSlot;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }
}
