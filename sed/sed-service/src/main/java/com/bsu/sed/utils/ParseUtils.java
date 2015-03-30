package com.bsu.sed.utils;

import java.util.Arrays;
import java.util.List;

/**
 * @author gbondarchuk
 */
public class ParseUtils {
    private static final String DEFAULT_DELIMITER = ",";

    public static List<String> split(String line, String delimiter) {
        String[] elements = line.split(delimiter);
        return Arrays.asList(elements);
    }

    public static List<String> split(String line) {
        return split(line, DEFAULT_DELIMITER);
    }
}
