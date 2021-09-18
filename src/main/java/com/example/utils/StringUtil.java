package com.example.utils;

public class StringUtil {

    public static boolean isEmpty(String str) {
        return str == null || "".equals(str.trim()) || str.equalsIgnoreCase("unDefined");
    }

    public static boolean isEmpty(String... str) {
        for (String s : str) {
            if (isEmpty(s)) {
                return true;
            }
        }
        return false;
    }
}
