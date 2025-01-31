package alpine.cryxie.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateFormatter{
        @SuppressWarnings("deprecation")
    private static final DateTimeFormatter BR_FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss", new Locale("pt", "BR"));

    public static String formatToBrazilian(LocalDateTime dateTime) {
        return dateTime.format(BR_FORMATTER);
    }

    public static String formatTo(LocalDateTime dateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return dateTime.format(formatter);
    }

    public static LocalDateTime parseFromBrazilian(String dateTimeStr) {
        return LocalDateTime.parse(dateTimeStr, BR_FORMATTER);
    }

    public static LocalDateTime parseFrom(String dateTimeStr, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(dateTimeStr, formatter);
    }
}