package com.comelystreet.mongodb.types;

public class StoreBookingListPageRequestDataModel {
    private Pagination pagination;
    private String storeId;
    private String startDate;
    private String endDate;
    
    public Pagination getPagination() {
        return pagination;
    }
    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
    public String getStoreId() {
        return storeId;
    }
    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
    @Override
    public String toString() {
        return "StoreBookingListRequestDataModel [pagination=" + pagination + ", storeId=" + storeId + ", startDate="
                + startDate + ", endDate=" + endDate + "]";
    }
    
}
