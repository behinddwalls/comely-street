package com.comelystreet.dao.mongodb.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "BookingServicesDetail")
public class BookingServicesDetail {

    @Id
    private String id;
    @DBRef
    private CustomerDetail customerDataModel;
    @DBRef
    private StoreDetail storeDataModel;
    private List<ServiceItemDetail> serviceItemDetails;
    private DateTimeSlotDetail proposedStartTime;
    private DateTimeSlotDetail proposedEndTime;
    private DateTimeSlotDetail actualStartTime;
    private DateTimeSlotDetail actualEndTime;
    private DateTimeSlotDetail customerInTime;
    private DateTimeSlotDetail customerOutTime;
    private String bookingStatus;
    private long totalPrice;
    private String currency;

    private ReviewDetail reviewDetail;
    private boolean reviewed;
    private boolean isObsoleteForReview;

    private CustomerCommunicationStatus customerCommunicationStatus;
    private ClientCommunicationStatus clientCommunicationStatus;

    private String customerName;
    private String customerMobileNumber;
    private String customerEmail;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CustomerDetail getCustomerDataModel() {
        return customerDataModel;
    }

    public void setCustomerDataModel(CustomerDetail customerDataModel) {
        this.customerDataModel = customerDataModel;
    }

    public StoreDetail getStoreDataModel() {
        return storeDataModel;
    }

    public void setStoreDataModel(StoreDetail storeDataModel) {
        this.storeDataModel = storeDataModel;
    }

    public DateTimeSlotDetail getProposedStartTime() {
        return proposedStartTime;
    }

    public void setProposedStartTime(DateTimeSlotDetail proposedStartTime) {
        this.proposedStartTime = proposedStartTime;
    }

    public DateTimeSlotDetail getProposedEndTime() {
        return proposedEndTime;
    }

    public void setProposedEndTime(DateTimeSlotDetail proposedEndTime) {
        this.proposedEndTime = proposedEndTime;
    }

    public DateTimeSlotDetail getActualStartTime() {
        return actualStartTime;
    }

    public void setActualStartTime(DateTimeSlotDetail actualStartTime) {
        this.actualStartTime = actualStartTime;
    }

    public DateTimeSlotDetail getActualEndTime() {
        return actualEndTime;
    }

    public void setActualEndTime(DateTimeSlotDetail actualEndTime) {
        this.actualEndTime = actualEndTime;
    }

    public DateTimeSlotDetail getCustomerInTime() {
        return customerInTime;
    }

    public void setCustomerInTime(DateTimeSlotDetail customerInTime) {
        this.customerInTime = customerInTime;
    }

    public DateTimeSlotDetail getCustomerOutTime() {
        return customerOutTime;
    }

    public void setCustomerOutTime(DateTimeSlotDetail customerOutTime) {
        this.customerOutTime = customerOutTime;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
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

    public List<ServiceItemDetail> getServiceItemDetails() {
        return serviceItemDetails;
    }

    public void setServiceItemDetails(List<ServiceItemDetail> serviceItemDetails) {
        this.serviceItemDetails = serviceItemDetails;
    }

    public CustomerCommunicationStatus getCustomerCommunicationStatus() {
        return customerCommunicationStatus;
    }

    public void setCustomerCommunicationStatus(CustomerCommunicationStatus customerCommunicationStatus) {
        this.customerCommunicationStatus = customerCommunicationStatus;
    }

    public ClientCommunicationStatus getClientCommunicationStatus() {
        return clientCommunicationStatus;
    }

    public void setClientCommunicationStatus(ClientCommunicationStatus clientCommunicationStatus) {
        this.clientCommunicationStatus = clientCommunicationStatus;
    }

    public ReviewDetail getReviewDetail() {
        return reviewDetail;
    }

    public void setReviewDetail(ReviewDetail reviewDetail) {
        this.reviewDetail = reviewDetail;
    }

    @Override
    public String toString() {
        return "BookingServicesDetail [id=" + id + ", customerDataModel=" + customerDataModel + ", storeDataModel="
                + storeDataModel + ", serviceItemDetails=" + serviceItemDetails + ", proposedStartTime="
                + proposedStartTime + ", proposedEndTime=" + proposedEndTime + ", actualStartTime=" + actualStartTime
                + ", actualEndTime=" + actualEndTime + ", customerInTime=" + customerInTime + ", customerOutTime="
                + customerOutTime + ", bookingStatus=" + bookingStatus + ", totalPrice=" + totalPrice + ", currency="
                + currency + ", reviewDetail=" + reviewDetail + ", reviewed=" + reviewed
                + ", customerCommunicationStatus=" + customerCommunicationStatus + ", clientCommunicationStatus="
                + clientCommunicationStatus + ", customerName=" + customerName + ", customerMobileNumber="
                + customerMobileNumber + ", customerEmail=" + customerEmail + "]";
    }

    public boolean isReviewed() {
        return reviewed;
    }

    public void setReviewed(boolean reviewed) {
        this.reviewed = reviewed;
    }

    public boolean isObsoleteForReview() {
        return isObsoleteForReview;
    }

    public void setObsoleteForReview(boolean isObsoleteForReview) {
        this.isObsoleteForReview = isObsoleteForReview;
    }

}
