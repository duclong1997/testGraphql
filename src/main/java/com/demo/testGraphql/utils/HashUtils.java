package com.demo.testGraphql.utils;

public class HashUtils {
    public HashUtils() {
    }

    public static String generateSalt() {
        return RandomString.getInstance().nextString();
    }

    public static String generateSalt(int number) {
        return (new RandomString(number)).nextString();
    }
}
