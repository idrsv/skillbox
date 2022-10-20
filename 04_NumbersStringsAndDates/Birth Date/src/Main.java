import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.time.DayOfWeek;


//public class Main {
//    public static void main(String args[]) {
//        DateFormat dateFormat = new SimpleDateFormat(" - dd.MM.yyyy - EEE", Locale.ENGLISH);
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(1997, Calendar.MAY, 20);
//
//        Date date = new Date();
//        Date date2 = calendar.getTime();
//
//        int i = 0;
//
//        while (date.compareTo(date2) > 0)
//        {
//            System.out.println(i + dateFormat.format(date2));
//            calendar.add(Calendar.YEAR, 1);
//            date2 = calendar.getTime();
//            i++;
//        }
//    }
//}

public class Main {
    public static void main(String args[]) {
        LocalDate date = LocalDate.now();
        LocalDate birthday = LocalDate.of(1997, 05,20);
        System.out.println("0 - " + DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(birthday));
        int i = 1;
        while (birthday.getYear() < date.getYear())
        {
            birthday = birthday.plusYears(1);
            String formattedDate = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(birthday);
            System.out.println(i + " - " + formattedDate);
            i++;
        }
    }
}
