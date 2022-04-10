package com.comelystreet.dao.mongodb.model;

import java.util.List;

import com.comelystreet.mongodb.types.Day;

public class StoreOperationalTimeDetail {

    // All times in 24hr format

    private long openTime;
    private long closeTime;
    private List<Day> openDays;

    // break Times
    private List<BreakTimeDetail> breakTimes;

    public long getOpenTime() {
        return openTime;
    }

    public void setOpenTime(long openTime) {
        this.openTime = openTime;
    }

    public long getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(long closeTime) {
        this.closeTime = closeTime;
    }

    public List<Day> getOpenDays() {
        return openDays;
    }

    public void setOpenDays(List<Day> openDays) {
        this.openDays = openDays;
    }

    public List<BreakTimeDetail> getBreakTimes() {
        return breakTimes;
    }

    public void setBreakTimes(List<BreakTimeDetail> breakTimes) {
        this.breakTimes = breakTimes;
    }

}
