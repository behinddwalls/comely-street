package com.comelystreet.dao.mongodb.model;

import java.util.LinkedHashSet;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "StoreDailyScheduleDetail")
public class StoreDailyScheduleDetail {

    @Id
    private String id;
    @DBRef
    private StoreDetail storeDetail;
    private long scheduleDate;
    private LinkedHashSet<TimeSlotDetail> timeSlotDetails;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(long scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public LinkedHashSet<TimeSlotDetail> getTimeSlotDetails() {
        return timeSlotDetails;
    }

    public void setTimeSlotDetails(LinkedHashSet<TimeSlotDetail> timeSlotDetails) {
        this.timeSlotDetails = timeSlotDetails;
    }

    public StoreDetail getStoreDetail() {
        return storeDetail;
    }

    public void setStoreDetail(StoreDetail storeDetail) {
        this.storeDetail = storeDetail;
    }

}
