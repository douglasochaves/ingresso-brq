package br.com.brq.brqingresso.common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Helpers {

    public static String convertDateToString(LocalDateTime date) {
        return date.toString();
    }

    public static LocalDate convertStringToDate(String string) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(string, formatter);
        return date;
    }

    public static Integer convertStringToInt(String string) {
        return Integer.parseInt(string);
    }
}
