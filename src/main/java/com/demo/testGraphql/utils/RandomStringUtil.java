package com.demo.testGraphql.utils;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class RandomStringUtil {
    private static RandomStringUtil DEFAULT_INSTANCE = null;
    public static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LOWER;
    public static final String DIGITS = "0123456789";
    public static final String alphanum;
    private final Random random;
    private final char[] symbols;
    private final char[] buf;

    public static RandomStringUtil getInstance() {
        if (DEFAULT_INSTANCE == null) {
            DEFAULT_INSTANCE = new RandomStringUtil();
        }

        return DEFAULT_INSTANCE;
    }

    public RandomStringUtil(int length, Random random, String symbols) {
        if (length < 1) {
            throw new IllegalArgumentException();
        } else if (symbols.length() < 2) {
            throw new IllegalArgumentException();
        } else {
            this.random = (Random) Objects.requireNonNull(random);
            this.symbols = symbols.toCharArray();
            this.buf = new char[length];
        }
    }

    public RandomStringUtil(int length, Random random) {
        this(length, random, alphanum);
    }

    public RandomStringUtil(int length) {
        this(length, new SecureRandom());
    }

    public RandomStringUtil() {
        this(16);
    }

    public String nextString() {
        for(int idx = 0; idx < this.buf.length; ++idx) {
            this.buf[idx] = this.symbols[this.random.nextInt(this.symbols.length)];
        }

        return new String(this.buf);
    }

    static {
        LOWER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toLowerCase(Locale.ROOT);
        alphanum = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + LOWER + "0123456789";
    }
}
