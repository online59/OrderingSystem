package com.example.orderingsystem.utils;

import java.util.Random;

public class MyUtils {

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
}
