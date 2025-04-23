package com.app.util;

public class utilFunctions {
    public static boolean isParsableToNumber(String str) {
        try {
            // Try parsing the string to an integer
            Integer.parseInt(str);
            return true; // If parsing succeeds, return true
        } catch (NumberFormatException e) {
            // If parsing fails, catch the exception and return false
            return false;
        }
    }

//    public static int isParsableToNumber(String str) {
//        try {
//            // Try parsing the string to an integer
//            int num = Integer.parseInt(str);
//            return num; // If parsing succeeds, return true
//        } catch (NumberFormatException e) {
//            // If parsing fails, catch the exception and return false
//            return null;
//        }
//    }
    public static Integer parseToNumber(String str) {
        try {
            // Try parsing the string to an integer and return the result
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            // If parsing fails, return null
            return null;
        }
    }
}
