package com.comelystreet.services.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

public class DateTimeUtility {

    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String DD_MMM_YYYY_SHORT_STRING = "dd MMM, yyyy";
    public static final String HHMM = "HHmm";

    public static String getStringFromDate(final Date date, final String format) {
        return new SimpleDateFormat(format).format(date);
    }

    public static Date getDateFromString(final String stringDate, final String format) throws ParseException {
        return new SimpleDateFormat(format).parse(stringDate);
    }

    public static String convertFromUIDateStringToDBDateString(final String uiStringDate) throws ParseException {
        return new SimpleDateFormat(YYYYMMDD)
                .format(new SimpleDateFormat(DD_MMM_YYYY_SHORT_STRING).parse(uiStringDate));
    }

    public static long convertFromUIDateStringToDBDateLong(final String uiStringDate) throws ParseException {
        return convertDateToLong(getDateFromString(uiStringDate, DD_MMM_YYYY_SHORT_STRING), YYYYMMDD);
    }

    public static long convertDateToLong(final Date date, final String format) {
        String dateString = getStringFromDate(date, format);
        return Long.parseLong(dateString);
    }

    public static String convertLongDateToUIStringDate(long longDate) throws ParseException {
        String stringDate = String.valueOf(longDate);
        Date date = getDateFromString(stringDate, YYYYMMDD);
        return getStringFromDate(date, DD_MMM_YYYY_SHORT_STRING);
    }

    public static String convertLongTimeToUIStringTime(long longTime) throws ParseException {
        return String.valueOf(longTime) + " Hrs";
    }

    public static String convertToUTCUsingJoda(String bookingDate) throws ParseException {
        DateTimeFormatter fmt = DateTimeFormat.forPattern(DD_MMM_YYYY_SHORT_STRING);
        DateTime date = fmt.parseDateTime(bookingDate);
        return date.toString();
    }

    public static String getCurrentUTCDateTime() {
        DateTime dt = new DateTime(DateTimeZone.UTC);
        return dt.toString();
    }

    public static String getCurrentDateAsUIDateString() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat(DD_MMM_YYYY_SHORT_STRING);
        df.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
        System.out.println("Date and time in India: " + df.format(date));
        return df.format(date);
    }

    public static String getCurrentDateAsDBDateString() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat(YYYYMMDD);
        df.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
        System.out.println("Date and time in India: " + df.format(date));
        return df.format(date);
    }

    // DO NOT USE THIS, not localized
    public static Date getCurrentDate() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat(DD_MMM_YYYY_SHORT_STRING);
        df.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
        System.out.println("Date and time in India: " + df.format(date));
        return date;
    }

    // Time utilities
    public static String getTimeFromLong(Long x) {
        long firstPart = ((x - (x % 100)) / 100);
        long lastPart = (x % 100);

        String timeSlotAsString = firstPart + ":" + (lastPart == 0 ? "00" : lastPart);
        return timeSlotAsString;
    }

    public static String getCurrentLocalTimeinDBFormat() {
        LocalTime localTime = new LocalTime(DateTimeZone.forID("+05:30"));
        String time = String.valueOf(localTime.getHourOfDay()) + String.valueOf(localTime.getMinuteOfHour());
        System.out.println("Current time: " + time);
        return time;
    }

    public static String addMinutesToCurrentTime(int minutes) {
        DateTimeFormatter outputFormat = new DateTimeFormatterBuilder().appendPattern("HHmm").toFormatter();
        LocalTime localTime = new LocalTime(DateTimeZone.forID("+05:30"));
        LocalTime newTime = localTime.plusMinutes(minutes);

        final String localTimeString = localTime.toString(outputFormat);
        final String newTimeString = newTime.toString(outputFormat);

        if (Long.valueOf(newTimeString) > Long.valueOf(localTimeString)) {
            return newTimeString;
        }
        return localTimeString;
    }

    public static long addMinutesToTime(long longTime, int minutes) {
        DateTimeFormatter outputFormat = new DateTimeFormatterBuilder().appendPattern("HHmm").toFormatter();

        String longTimeString = String.valueOf(longTime);

        if (!StringUtils.isEmpty(longTimeString) && longTimeString.length() == 3) {
            longTimeString = "0" + longTimeString;
        }

        DateTime time = outputFormat.parseDateTime(longTimeString);

        time = time.plusMinutes(minutes);

        String timeString = time.toString(outputFormat);

        System.out.println("Add minutes " + minutes + " to time " + longTime + ". And the output is: " + timeString);
        return Long.valueOf(timeString);
    }

}
