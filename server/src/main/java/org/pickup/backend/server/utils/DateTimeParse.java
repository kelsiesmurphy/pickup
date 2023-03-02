package org.pickup.backend.server.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeParse {

    private static final String jsonFormat = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(jsonFormat);

    public static LocalDateTime fromString(String input) {
        try {
            return LocalDateTime.parse(input, formatter);
        }
        catch (Exception e) {
            return null;
        }
    }

    public static String toString(LocalDateTime input) {
        try {
            return input.format(formatter);
        }
        catch (Exception e) {
            return null;
        }
    }
}
