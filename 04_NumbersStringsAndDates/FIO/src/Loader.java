import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Loader {
    public static void main(String[] args) {
//        System.out.println("Введите: Фамилию Имя Отчество");
////        Scanner scanner = new Scanner(System.in);
////        String fullName = scanner.nextLine();
////        int space = 0;
////        int fullName2 = 0;
////
////        for (int i = 0; i < fullName.length(); i++) {
////            if (Character.isWhitespace(fullName.charAt(i))) {
////                space++;
////            }
////            if ((fullName.charAt(i) >= '0' && fullName.charAt(i) <= '9')) {
////                fullName2 = 1;
////            }
////        }
////        if (fullName2 == 1 || space != 2) {
////            System.out.println("Вы допустили ошибку при вводе данных");
////        }
////            else {
////                String[] sentences = fullName.split("\\s");
////                System.out.println("Фамилия: " + sentences[0]);
////                System.out.println("Имя: " + sentences[1]);
////                System.out.println("Отчество: " + sentences[2]);
////            }
        System.out.println("Введите: Фамилию Имя Отчество");
        Scanner scanner = new Scanner(System.in);

        final String regex = "([А-Яа-я]+) ([А-Яа-я]+) ([А-Яа-я]+)";
        final String fullName = scanner.nextLine();

        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(fullName);

        if (matcher.find()) {
            System.out.println("Фамилия: " + matcher.group(1));
            System.out.println("Имя: " + matcher.group(2));
            System.out.println("Отчество: " +matcher.group(3));
        }
        else {
            System.out.println("Вы допустили ошибку при вводе данных");
        }
    }
}
