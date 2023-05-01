package com.example.orderingsystem.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MyUtils {

    private static final String dateFormat = "yyyy-MM-dd";

    public static String addItemsWithSlashSeparator(String...str) {
        int expectedLength = 0;
        for (String item : str) {
            expectedLength += item.length() + 1;
        }
        StringBuilder result = new StringBuilder(expectedLength);
        StringBuilder separator = new StringBuilder("/");
        for (String item : str) {
            if (result.length() > 0) {
                result.append(separator);
            }
            result.append(item);
        }
        return result.toString();
    }


    public static boolean isFieldsNull(String...str) {
        for (String value: str) {
            if (value == null || value.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public static boolean isStringMatch(String str1, String str2) {
        return !str1.equals(str2);
    }

    public static float getRandomFloat() {
        return new Random().nextFloat();
    }

    public static float getRandomInt() {
        return new Random().nextInt();
    }

    public static String getFormattedDate(long timeStamp) {
        Date date = new Date(timeStamp);
        return new SimpleDateFormat(dateFormat, Locale.US).format(date);
    }

    public static long getTimeStamp(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        Date date = null;
        try {
            date = dateFormat.parse(dateString);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return date.getTime() / 1000;
    }

    public static String[] getDatesInRange(int startDate, int startMonth, int startYear, int dateRange) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, startMonth - 1);
        calendar.set(Calendar.YEAR, startYear);
        calendar.set(Calendar.DAY_OF_MONTH, startDate);

        String[] dateList = new String[dateRange];
        for (int day = 0; day < dateRange; day++) {

            dateList[day] = dateFormat.format(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);

        }

        return dateList;
    }
}
