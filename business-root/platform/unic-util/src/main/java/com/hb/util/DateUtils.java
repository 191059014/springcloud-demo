package com.hb.util;

import com.fasterxml.jackson.databind.util.StdDateFormat;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtils {

    protected static Logger logger = LoggerFactory.getLogger(DateUtils.class);
    private static Date maxDate;
    private static Timestamp maxTimestamp;

    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static Long getCurrentNanoTime() {
        return Long.valueOf(System.nanoTime());
    }

    public static Timestamp getMaxTimestamp() {
        return maxTimestamp;
    }

    public static Date getCurrentDate() {
        Date date = new Date(System.currentTimeMillis());

        return date;
    }

    public static Date getMaxDate() {
        return maxDate;
    }

    public static int getYearsBetween(Date a, Date b) {
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(a);
        calendar2.setTime(b);
        int year = calendar1.get(1) - calendar2.get(1);

        if ((calendar1.get(2) < calendar2.get(2)) || (
                (calendar1
                        .get(2) ==
                        calendar2.get(2)) &&
                        (calendar1
                                .get(5) <
                                calendar2.get(5))))
            year--;
        return year;
    }

    public static int getFloorMonthsBetween(Date a, Date b) {
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(a);
        calendar2.setTime(b);
        int year = calendar1.get(1) - calendar2.get(1);
        int month = calendar1.get(2) - calendar2.get(2);
        month = year * 12 + month;

        if (calendar1.get(5) < calendar2.get(5))
            month--;
        return month;
    }

    public static int getCeilMonthsBetween(Date a, Date b) {
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(a);
        calendar2.setTime(b);
        int year = calendar1.get(1) - calendar2.get(1);
        int month = calendar1.get(2) - calendar2.get(2);
        month = year * 12 + month;

        if (calendar1.get(5) > calendar2.get(5))
            month++;
        return month;
    }

    public static int getDaysBetween(Date a, Date b) {
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(a);
        calendar2.setTime(b);
        long mSeconds = calendar1.getTimeInMillis() - calendar2.getTimeInMillis();
        int days = (int) (mSeconds / 1000L / 60L / 60L / 24L);
        return days;
    }

    public static Date addYear(Date a, int n) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(a);
        calendar1.add(1, n);
        return calendar1.getTime();
    }

    public static Date addMonth(Date a, int n) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(a);
        calendar1.add(2, n);
        return calendar1.getTime();
    }

    public static Date addDay(Date a, int n) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(a);
        calendar1.add(5, n);
        return calendar1.getTime();
    }

    public static int getResidueDaysBetween(Date a, Date b) {
        int year = getYearsBetween(a, b);
        Date c = addYear(b, year);
        return getDaysBetween(a, c);
    }

    public static Date getSqlDateByUtilDate(Date date) {
        if (date == null) {
            return null;
        }
        return new Date(date.getTime());
    }

    public static Date getSimpleDate(Date date) {
        if (date == null) {
            date = new Date();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        return calendar.getTime();
    }

    public static Date getStartDate(Date date) {
        if (date == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTime();
    }

    public static Date getEndDate(Date date) {
        if (date == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        calendar.set(14, 999);
        return calendar.getTime();
    }

    public static Date getCurrentDay() {
        Date date = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        calendar.set(14, 999);
        return calendar.getTime();
    }

    public static Calendar getCalendar(Date date) {
        if (date == null)
            return null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static Calendar getCurrentCalendar() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static Date getCurrentTime() {
        return new Date(System.currentTimeMillis());
    }

    public static String getTimeByCustomPattern(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    public static String getCurrentTimeByCustomPattern(String pattern) {
        return new SimpleDateFormat(pattern).format(new Date());
    }

    public static boolean isEndOfSeason(Date date) {
        Calendar calendar = getCalendar(date);
        int month = calendar.get(2);
        if (month % 3 == 2) {
            return true;
        }
        return false;
    }

    public static int getMonth(Date date) {
        Calendar calendar = getCalendar(date);
        return calendar.get(2) + 1;
    }

    public static int getDay(Date date) {
        Calendar calendar = getCalendar(date);
        return calendar.get(5);
    }

    public static int getYear(Date date) {
        Calendar calendar = getCalendar(date);
        return calendar.get(1);
    }

    public static int getMonthLength(Date date) {
        Calendar calendar = getCalendar(date);
        return calendar.getActualMaximum(5);
    }

    public static Date getActualMaximumDate(Date date) {
        Calendar calendar = getCalendar(date);
        int actualMaximumDay = calendar.getActualMaximum(5);
        calendar.set(5, actualMaximumDay);
        return calendar.getTime();
    }

    public static Date getActualMinimumDate(Date date) {
        Calendar calendar = getCalendar(date);
        int actualMininumDay = calendar.getActualMinimum(5);
        calendar.set(5, actualMininumDay);
        return calendar.getTime();
    }

    public static Date getSpecifyDate(Date date, int indexDay) {
        Calendar calendar = getCalendar(date);
        int actualMininumDay = calendar.getActualMinimum(5);
        calendar.set(5, actualMininumDay + indexDay - 1);
        return calendar.getTime();
    }

    public static String getYearMonth(Date date) {
        return new SimpleDateFormat("yyyy-MM").format(date);
    }

    public static Date getPriorMonthDate(Date date) {
        Calendar calendar = getCalendar(date);
        int month = calendar.get(2);
        int year = calendar.get(1);
        Calendar newCalendar = Calendar.getInstance();
        if (month == 0) {
            year--;
            month = 11;
        } else {
            month--;
        }
        newCalendar.set(year, month, 1);
        return newCalendar.getTime();
    }

    public static String getPriorMonth(String monthly) {
        Date date = getFirstDayOfThisMonth(monthly);
        date = getPriorMonthDate(date);

        return getYearMonth(date);
    }

    public static Date getPriorMonthDateByMonth(Date date) {
        Calendar calendar = getCalendar(date);
        int month = calendar.get(2) + 1;
        int year = calendar.get(1);
        Calendar newCalendar = Calendar.getInstance();

        if ((month == 4) || (month == 5) || (month == 6)) {
            newCalendar.set(year, 3, 0);
            return newCalendar.getTime();
        }
        if ((month == 7) || (month == 8) || (month == 9)) {
            newCalendar.set(year, 6, 0);
            return newCalendar.getTime();
        }
        if ((month == 10) || (month == 11) || (month == 12)) {
            newCalendar.set(year, 9, 0);
            return newCalendar.getTime();
        }
        year--;
        newCalendar.set(year, 12, 0);
        return newCalendar.getTime();
    }

    public static Date getPerforMancebonusMonthDateByMonth(Date date) {
        Calendar calendar = getCalendar(date);
        int month = calendar.get(2) + 1;
        int year = calendar.get(1);
        Calendar newCalendar = Calendar.getInstance();
        if ((month == 3) || (month == 4) || (month == 5)) {
            newCalendar.set(year, 3, 0);
            return newCalendar.getTime();
        }
        if ((month == 6) || (month == 7) || (month == 8)) {
            newCalendar.set(year, 6, 0);
            return newCalendar.getTime();
        }
        if ((month == 9) || (month == 10) || (month == 11)) {
            newCalendar.set(year, 9, 0);
            return newCalendar.getTime();
        }
        year--;
        newCalendar.set(year, 12, 0);
        return newCalendar.getTime();
    }

    public static Date getLastMonth(Date date) {
        Calendar calendar = getCalendar(date);
        int month = calendar.get(2);
        calendar.set(2, month - 1);
        calendar.set(5, 1);
        return calendar.getTime();
    }

    public static Date getNextMonth(Date date) {
        Calendar calendar = getCalendar(date);
        int month = calendar.get(2);
        calendar.set(2, month + 1);
        calendar.set(5, 1);
        return getActualMaximumDate(calendar.getTime());
    }

    public static String getNextMonth(String monthly) {
        Date date = getFirstDayOfThisMonth(monthly);
        date = getNextMonth(date);

        return getYearMonth(date);
    }

    public static Date parse(String str, String pattern) {
        if (str == null)
            return null;
        try {
            return new SimpleDateFormat(pattern, Locale.US).parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            if (logger.isErrorEnabled()) {
                logger.error("type conversion failed", e);
            }
            throw new RuntimeException(e);
        }
    }

    public static Date parse(Date date, int months) {
        String str = getTimeByCustomPattern(date, "yyyy-MM");
        Date d = parse(str, "yyyy-MM");
        Calendar calendar = getCalendar(d);
        int month = calendar.get(2);
        calendar.set(2, month - months);
        return calendar.getTime();
    }

    public static boolean isEndQuarter(Date date) {
        boolean retval = false;
        Calendar calendar = getCalendar(date);
        int month = calendar.get(2);
        if ((month + 1) % 3 == 0) {
            retval = true;
        }
        return retval;
    }

    public static boolean isMidYear(Date date) {
        boolean retval = false;
        Calendar calendar = getCalendar(date);
        int month = calendar.get(2);
        if ((month + 1) % 6 == 0) {
            retval = true;
        }
        return retval;
    }

    public static boolean isEndYear(Date date) {
        boolean retval = false;
        Calendar calendar = getCalendar(date);
        int month = calendar.get(2);
        if ((month + 1) % 12 == 0) {
            retval = true;
        }
        return retval;
    }

    public static String convertToDateString(Object exeDate) {
        String exeDateStr = "";
        if ((exeDate != null) && ((exeDate instanceof String))) {
            long dataValue = Long.parseLong((String) exeDate);
            Date date = new Date(dataValue);
            exeDateStr = getTimeByCustomPattern(date, "yyyy-MM-dd");
        }
        return exeDateStr;
    }

    public static Date getDate(Date date, String dateType, int dateValue) {
        if (date == null) {
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (dateType.equals("DATE"))
            calendar.set(6, calendar.get(6) + dateValue);
        else if (dateType.equals("MONTH"))
            calendar.set(2, calendar.get(2) + dateValue);
        else if (dateType.equals("YEAR")) {
            calendar.set(1, calendar.get(1) + dateValue);
        }

        return getSimpleDate(calendar.getTime());
    }

    public static Date getNextYear(Date date, int yearNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(1, calendar.get(1) + yearNum);
        calendar.set(6, calendar.get(6));
        return calendar.getTime();
    }

    public static Date getNextDateByMonth(Date date, int monthNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(2, calendar.get(2) + monthNum);
        calendar.set(6, calendar.get(6));
        return calendar.getTime();
    }

    public static Date getNextDay(Date date, int dayNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(6, calendar.get(6) + dayNum);
        return calendar.getTime();
    }

    public static Date getFirstDayOfLastMonth(Date date, int monthNum, int dayNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(2, calendar.get(2) + monthNum);
        calendar.set(5, dayNum);
        return calendar.getTime();
    }

    public static Date getLastDayOfThisMonth(String strDate) {
        try {
            Date date = new SimpleDateFormat("yyyy-MM").parse(strDate.trim());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(2, calendar.get(2) + 1);
            calendar.set(5, 1);
            calendar.add(5, -1);
            calendar.set(11, 23);
            calendar.set(13, 59);
            calendar.set(12, 59);

            return calendar.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            if (logger.isErrorEnabled()) {
                logger.error("Date parse error", e);
            }
        }

        return getCurrentDate();
    }

    public static Date getLastDayOfThisMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(2, calendar.get(2) + 1);
        calendar.set(5, 1);
        calendar.add(5, -1);
        calendar.set(11, 23);
        calendar.set(13, 59);
        calendar.set(12, 59);

        return calendar.getTime();
    }

    public static void main(String[] args) {
        Date date = getMaxTimestamp();
        System.out.println(JsonUtils.toJsonString(date, Boolean.valueOf(true)));
    }

    public static Date getFirstDayOfThisMonth(String strDate) {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(strDate.trim() + "-01");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(11, 0);
            calendar.set(13, 0);
            calendar.set(12, 0);

            return calendar.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            if (logger.isErrorEnabled()) {
                logger.error("Date parse error", e);
            }
        }

        return getCurrentDate();
    }

    public static Date getFirstDayOfThisMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(5, 1);
        calendar.set(11, 0);
        calendar.set(13, 0);
        calendar.set(12, 0);

        return calendar.getTime();
    }

    public static Object checkPlanTermDate(Date startDate, Date endDate) {
        Calendar calenderStart = Calendar.getInstance();
        calenderStart.setTime(getSimpleDate(startDate));
        Calendar calenderEnd = Calendar.getInstance();
        calenderEnd.setTime(getSimpleDate(endDate));
        StringBuffer sm = new StringBuffer();
        if ((calenderStart.after(calenderEnd)) || (calenderStart.equals(calenderEnd)))
            sm.append("!");
        if (calenderStart.get(2) != calenderEnd.get(2))
            sm.append("!");
        if (sm.length() != 0) {
            return sm;
        }
        return null;
    }

    public static Map getDateRange(Date data) {
        Map dates = new HashMap();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        calendar.set(11, 0);
        calendar.set(13, 0);
        calendar.set(12, 0);
        dates.put("DATEBEGIN", calendar.getTime());
        calendar.setTime(data);
        calendar.set(11, 23);
        calendar.set(13, 59);
        calendar.set(12, 59);
        dates.put("DATEEND", calendar.getTime());
        return dates;
    }

    public static String getTimeByPattern(Date date, String formatType) {
        return new SimpleDateFormat(formatType).format(date);
    }

    public static String getTimeByPattern(Date date, String formatType, Locale locale) {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        if (date == null) {
            date = new Date();
        }
        return new SimpleDateFormat(formatType, locale).format(date);
    }

    public static Date getDateByString(String strDate, String strFormate)
            throws ParseException {
        SimpleDateFormat simpleFormat = new SimpleDateFormat(strFormate);

        return simpleFormat.parse(strDate);
    }

    public static Date plusHours(Date date, int hours) {
        DateTime dateTime = new DateTime(date.getTime());
        dateTime.plusHours(hours);

        return dateTime.toDate();
    }

    static {
        try {
            maxDate = new StdDateFormat().parse("9999-12-31T23:59:59.999+0800");
            maxTimestamp = new Timestamp(maxDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}