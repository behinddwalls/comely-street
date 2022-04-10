package com.comelystreet.dao.mongodb.model;

public class BreakTimeDetail {
    private long startTime;
    private long endTime;

    public BreakTimeDetail() {
    }

    public BreakTimeDetail(long startTime, long endTime) {
        super();
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "BreakTimeDataModel [startTime=" + startTime + ", endTime=" + endTime + "]";
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
