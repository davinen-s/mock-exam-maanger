package edu.exam.manager.utils;

import edu.exam.manager.model.Examinee;

import java.util.*;

/**
 * Created by Davi on 7/4/2017.
 */
public class CalendarUtils {


    public static List<String> getWorkingDays(Date startDate, Date endDate) {

        List<String> dateList = new ArrayList<>();

        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);

        while (startCal.getTimeInMillis() <= endCal.getTimeInMillis()) {
            if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY &&
                    startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
                dateList.add(startCal.getTime().toString());
            }

            startCal.add(Calendar.DAY_OF_MONTH, 1);
        }


        return dateList;

    }


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
                examDates.put(startCal.getTime().toString(), new ArrayList<>());
            }

            startCal.add(Calendar.DAY_OF_MONTH, 1);
        }

        return examDates;

    }

}
