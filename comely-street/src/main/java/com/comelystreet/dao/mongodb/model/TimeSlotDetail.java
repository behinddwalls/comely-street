package com.comelystreet.dao.mongodb.model;

public class TimeSlotDetail {
    private long timeSlot;
    private int slotsAvailable;

    public long getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(long timeSlot) {
        this.timeSlot = timeSlot;
    }

    public int getSlotsAvailable() {
        return slotsAvailable;
    }

    public void setSlotsAvailable(int slotsAvailable) {
        this.slotsAvailable = slotsAvailable;
    }

    public TimeSlotDetail(long timeSlot, int slotsAvailable) {
        super();
        this.timeSlot = timeSlot;
        this.slotsAvailable = slotsAvailable;
    }

    @Override
    public String toString() {
        return "TimeSlotDataModel [timeSlot=" + timeSlot + ", slotsAvailable=" + slotsAvailable + "]";
    }

}
