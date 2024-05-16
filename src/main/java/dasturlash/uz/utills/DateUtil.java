package dasturlash.uz.utills;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

public class DateUtil {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm");

    public static String toSimpleFormat(LocalDateTime localDateTime) {
        return formatter.format(localDateTime);
    }


    public static String toDateAndYearString(LocalDate localDate) { // 2024-12-01
        int month = localDate.get(ChronoField.MONTH_OF_YEAR);
        int year = localDate.get(ChronoField.YEAR); // 2024
        return month + "/" + (year%2000);
    }
}
