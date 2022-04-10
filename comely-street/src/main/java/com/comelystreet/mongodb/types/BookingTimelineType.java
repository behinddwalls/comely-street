package com.comelystreet.mongodb.types;

public enum BookingTimelineType {
    TODAYS("todays"), UPCOMING("upcoming"), PAST("past");

    private final String timelineType;

    private BookingTimelineType(final String timelineType) {
        this.timelineType = timelineType;
    }

    public String getTimelineType() {
        return timelineType;
    }
}
