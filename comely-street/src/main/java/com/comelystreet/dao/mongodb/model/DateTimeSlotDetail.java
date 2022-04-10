package com.comelystreet.dao.mongodb.model;

public class DateTimeSlotDetail {
	private long date;
    private long timeSlot;
    	
	public DateTimeSlotDetail(long date, long timeSlot) {
		super();
		this.date = date;
		this.timeSlot = timeSlot;
	}
	
    public long getDate() {
		return date;
	}
	
	public void setDate(long date) {
		this.date = date;
	}
	
	public long getTimeSlot() {
		return timeSlot;
	}
	
	public void setTimeSlot(long timeSlot) {
		this.timeSlot = timeSlot;
	}
	
	@Override
	public String toString() {
		return "DateTimeSlotDetail [date=" + date + ", timeSlot=" + timeSlot + "]";
    }

}
