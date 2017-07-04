package edu.exam.manager.utils;

import edu.exam.manager.model.Examinee;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Davi on 7/4/2017.
 */
public class CalendarUtils {


    /**
     * Generate a list of working days.
     * @param startDate the date as from which the working days will be fetched.
     * @param endDate the last date to which the working days will be fetched.
     * @return List of working days within the specified range.
     */
    public static List<String> getWorkingDays(Date startDate, Date endDate) {

        List<String> dateList = new ArrayList<>();

        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);

        while (startCal.getTimeInMillis() <= endCal.getTimeInMillis()) {
            if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY &&
                    startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
                dateList.add(getFormattedDateString(startCal.getTime()));
            }

            startCal.add(Calendar.DAY_OF_MONTH, 1);
        }


        return dateList;

    }


    /**
     * @param startDate start date
     * @param endDate last date
     * @return examDates, Map of Examinee list with date as key.
     */
    public static HashMap<String, List<Examinee>> getWorkingDaysMap(Date startDate, Date endDate) {

        HashMap<String, List<Examinee>> examDates = new HashMap<>();
        List<Date> dateList = new ArrayList<>();

        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);

        while (startCal.getTimeInMillis() <= endCal.getTimeInMillis()) {
            if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY &&
                    startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
                examDates.put(getFormattedDateString(startCal.getTime()), new ArrayList<>());
            }

            startCal.add(Calendar.DAY_OF_MONTH, 1);
        }

        return examDates;

    }


    /**
     * Convert a date object to a Formatted String date.
     * @param dateToFormat the date to format
     * @return the formatted date in string.
     */
    public static String getFormattedDateString(Date dateToFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE dd MMMM");

        return simpleDateFormat.format( dateToFormat );
    }

    /**
     * Generate a Date Object by the specified day and month.
     * Year is defaulted to 2017 and time to 0:00 00.0.
     * @param day The day of the month to set
     * @param month The month to set
     * @return A Date object.
     */
    public static Date getDate(int day, int month) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        return calendar.getTime();
    }

}
