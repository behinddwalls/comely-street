package com.comelystreet.mongodb.types;

import java.util.List;

import com.comelystreet.dao.mongodb.model.ServiceItemDetail;

public class BookingServicesDataModelOutput {
    private String bookingId;
    private String bookingDate;
    private String bookingTimeSlot;
    private String bookingStatus;
    private List<ServiceItemDetail> selectedServices;
    private long totalPrice;
    private String currency;
    
    private String storeId;
    private String storeName;
    private String storeArea;
    private String storeAddress;
    private String storeMobileNumber;
    private String storeOtherContactNumbers;

    private String customerName;
    private String customerMobileNumber;
    private String customerEmail;
    
    public String getStoreId() {
        return storeId;
    }
    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
    public String getStoreName() {
        return storeName;
    }
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
    public String getStoreArea() {
        return storeArea;
    }
    public void setStoreArea(String storeArea) {
        this.storeArea = storeArea;
    }
    public String getStoreAddress() {
        return storeAddress;
    }
    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }
    public String getStoreMobileNumber() {
        return storeMobileNumber;
    }
    public void setStoreMobileNumber(String storeMobileNumber) {
        this.storeMobileNumber = storeMobileNumber;
    }
    public String getStoreOtherContactNumbers() {
        return storeOtherContactNumbers;
    }
    public void setStoreOtherContactNumbers(String storeOtherContactNumbers) {
        this.storeOtherContactNumbers = storeOtherContactNumbers;
    }
    public String getBookingDate() {
        return bookingDate;
    }
    public String getBookingId() {
        return bookingId;
    }
    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }
    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }
    public String getBookingTimeSlot() {
        return bookingTimeSlot;
    }
    public String getBookingStatus() {
        return bookingStatus;
    }
    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
    public void setBookingTimeSlot(String bookingTimeSlot) {
        this.bookingTimeSlot = bookingTimeSlot;
    }
    public List<ServiceItemDetail> getSelectedServices() {
        return selectedServices;
    }
    public void setSelectedServices(List<ServiceItemDetail> selectedServices) {
        this.selectedServices = selectedServices;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getCustomerMobileNumber() {
        return customerMobileNumber;
    }
    public void setCustomerMobileNumber(String customerMobileNumber) {
        this.customerMobileNumber = customerMobileNumber;
    }
    public String getCustomerEmail() {
        return customerEmail;
    }
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
    public long getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
