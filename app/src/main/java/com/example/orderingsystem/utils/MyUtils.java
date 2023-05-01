package com.example.orderingsystem.utils;

import java.util.Date;
import java.util.Random;

public class MyUtils {

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

    public static Date getDate(long timeStamp) {
        return new Date(timeStamp);
    }
}
