package com.demo.testGraphql.utils;

public class HashUtil {
    public HashUtil() {
    }

    public static String generateSalt() {
        return RandomStringUtil.getInstance().nextString();
    }

    public static String generateSalt(int number) {
        return (new RandomStringUtil(number)).nextString();
    }
}
