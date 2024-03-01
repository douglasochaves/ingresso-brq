package br.com.brq.brqingresso.common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Helpers {

    public static String convertDateToString(LocalDateTime date) {
        if(date == null) return null;
        return date.toString();
    }

    public static LocalDate convertStringToDate(String string) {
        if(string == null) return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(string, formatter);
        return date;
    }

    public static LocalDateTime convertStringToDateTime(String string) {
        if(string == null) return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        LocalDateTime dateTime = LocalDateTime.parse(string, formatter);
        return dateTime;
    }

    public static Integer convertStringToInt(String string) {
        if(string == null) return null;
        return Integer.parseInt(string);
    }

    public static String dataHoraAtualFormatada() {
        ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        return zonedDateTime.format(formatter);
    }

    public static String dataHoraFormatada(LocalDateTime dateTime) {
        if(dateTime == null) return null;
        ZonedDateTime zonedDateTime = ZonedDateTime.of(dateTime, ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        return zonedDateTime.format(formatter);
    }
}
