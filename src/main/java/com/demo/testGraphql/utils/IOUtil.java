package com.demo.testGraphql.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOUtil {
    public static void closeQuietly(InputStream input) throws IOException {
        if (input != null) {
            input.close();
        }
    }

    public static void closeQuietly(OutputStream output) throws IOException {
        if (output != null) {
            output.close();
        }
    }
}
