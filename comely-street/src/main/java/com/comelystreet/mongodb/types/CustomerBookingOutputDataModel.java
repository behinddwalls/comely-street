package com.comelystreet.mongodb.types;

import java.util.List;

import com.comelystreet.dao.mongodb.model.ServiceItemDetail;

public class CustomerBookingOutputDataModel {

    private String bookingId;
    private StoreDataModel storeDataModel;
    private List<ServiceItemDetail> serviceItemDetails;
    private String date;
    private String time;
    private String bookingStatus;
    private long totalPrice;

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public StoreDataModel getStoreDataModel() {
        return storeDataModel;
    }

    public void setStoreDataModel(StoreDataModel storeDataModel) {
        this.storeDataModel = storeDataModel;
    }

    public List<ServiceItemDetail> getServiceItemDetails() {
        return serviceItemDetails;
    }

    public void setServiceItemDetails(List<ServiceItemDetail> serviceItemDetails) {
        this.serviceItemDetails = serviceItemDetails;
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

    @Override
    public String toString() {
        return "CustomerBookingOutputDataModel [bookingId=" + bookingId + ", storeDataModel=" + storeDataModel
                + ", serviceItemDetails=" + serviceItemDetails + ", date=" + date + ", time=" + time
                + ", bookingStatus=" + bookingStatus + ", totalPrice=" + totalPrice + "]";
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
