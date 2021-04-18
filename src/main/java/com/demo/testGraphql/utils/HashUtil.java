package com.demo.testGraphql.utils;

public class HashUtil {
    public HashUtil() {
    }

    public static String generateSalt() {
        return RandomString.getInstance().nextString();
    }

    public static String generateSalt(int number) {
        return (new RandomString(number)).nextString();
    }
}
