import java.util.Scanner;

public class Loader {
    public static void main(String[] args) {
        System.out.println("Введите номер телефона");
        Scanner scanner = new Scanner(System.in);
        String phoneNumber = scanner.nextLine();

        if ((phoneNumber.replaceAll("[^0-9]", "").trim()).length() == 11) {
            String phoneNumber1 = phoneNumber.replaceAll("[^0-9]", "").trim();
            System.out.println("+7" + " " + phoneNumber1.substring(1, 4) + " " + phoneNumber1.substring(4, 7) +
                    "-" + phoneNumber1.substring(7, 9) + "-" + phoneNumber1.substring(9, 11));
        }
        else if ((phoneNumber.replaceAll("[^0-9]", "").trim()).length() == 10)
        {
            String phoneNumber2 = phoneNumber.replaceAll("[^0-9]","").trim();
            System.out.println("+7" + " " + phoneNumber2.substring(0, 3) + " " + phoneNumber2.substring(3,6) +
                    "-" + phoneNumber2.substring(6, 8) + "-" + phoneNumber2.substring(8, 10));
        }
        // +7 903 123-45-67
    }
}
