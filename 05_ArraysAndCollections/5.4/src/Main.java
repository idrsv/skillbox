import java.util.*;
public class Main {
    private static TreeMap<String, String> phoneBook = new TreeMap<>();
    public static void main(String[] args) {
        // Map <K, V> Данил 89853685585
        Scanner scanner = new Scanner(System.in);
        for (; ; ) {
            String input = scanner.nextLine();
            String nameOrNumber = input.trim();
            String subscribersName = nameOrNumber.replaceAll("([\\d]+)", "").trim();
            String subscribersPhoneNumber = nameOrNumber.replaceAll("([А-Яа-я]+)", "").trim();

            if (nameOrNumber.equals("LIST")) {
                printPhoneBook(phoneBook);
                continue; }
            if (phoneBook.containsKey(subscribersName)) {
                System.out.println("Хотите перезаписать номер абонента? : 1 - yes, 2 - no ");
                Scanner scanner2 = new Scanner(System.in);
                String input2 = scanner2.nextLine();
                if (input2.startsWith("1")){
                    System.out.println("Введите номер абонента: ");
                    String phone = scanner.nextLine();
                    phoneBook.put(subscribersName,phone); }
                   else if (input2.startsWith("2")) {
                    System.out.println("Абонент - " + subscribersName + " с номером " + phoneBook.get(subscribersName) + " добавлен в телефонную книгу"); }
            } else if (phoneBook.containsValue(subscribersPhoneNumber)) {
                System.out.println("Хотите перезаписать имя абонента? : 1 - yes, 2 - no ");
                Scanner scanner3 = new Scanner(System.in);
                String input3 = scanner3.nextLine();
                if (input3.startsWith("1")){
                    System.out.println("Введите имя абонента: ");
                    String name = scanner.nextLine();
                    phoneBook.put(subscribersName,name);
                }
                else if (input3.startsWith("2")) {
                    for (Map.Entry<String, String> entry : phoneBook.entrySet()){
                        if (entry.getValue().equals(subscribersPhoneNumber)){
                            System.out.println("По номеру " + subscribersPhoneNumber + " зарегистрирован абонент - " + entry.getKey()); }
                    }
                }
            } else if (subscribersPhoneNumber.isEmpty()){
                System.out.println("Введите номер абонента: ");
                String phone = scanner.nextLine();
                phoneBook.put(subscribersName,phone); }
            else if (subscribersName.isEmpty()){
                System.out.println("Вы не ввели имя абонента: ");
                String name = scanner.nextLine();
                phoneBook.put(name, subscribersPhoneNumber);}
            else phoneBook.put(subscribersName, subscribersPhoneNumber);
        }
    }

    private static void printPhoneBook(Map<String, String> map) {
        System.out.println("Телефонный справочник: ");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }
}
