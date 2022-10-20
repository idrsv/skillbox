import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static final ArrayList<String> todoList = new ArrayList<>() {{
        add(0, "Сходить в магазин");
        add(1, "Сходить на тренировку");
        add(2, "Сделать домашнюю работу на Skillbox.ru");
        add(3, "Отнести посылку на почту");
    }};
    public static void main(String[] args) {

        System.out.println("\n-------------------------------- \nВведите команду: \nADD \nEDIT \nDELETE \nLIST \n--------------------------------");
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine() + " ";

            String command = input.substring(0,input.indexOf(" ")).toUpperCase().trim();
            String numberAndDescription = input.substring(command.length()).trim();
            String number = numberAndDescription.replaceAll("[А-Яа-я]|[A-Za-z]\\w]+","");
            String description = numberAndDescription.replaceAll("[0-9]+","");

            switch (command) {
                case "ADD":
                    if (number.equals("")){
                        todoList.add(description);
                    }
                    else if (Integer.parseInt(number) > todoList.size()){
                        todoList.add(description);
                    }
                    else {
                        todoList.add(Integer.parseInt(number),description);
                    }
                    break;
                case "EDIT":
                    if (Integer.parseInt(number) < todoList.size()){
                        todoList.set(Integer.parseInt(number),description);}
                    else {
                        System.out.println("Такого дела в вашем списке нет");
                    }
                    break;
                case "DELETE":
                    if (Integer.parseInt(number) < todoList.size()){
                        todoList.remove(Integer.parseInt(number));}
                    else {
                        System.out.println("Такого дела в вашем списке нет");
                    }
                    break;
                case "LIST":
                    System.out.println("Ваш список дел: ");
                    for (int i = 0; i < todoList.size(); i++) {
                        System.out.println((i + 1) + " " + todoList.get(i));
                    }
                    break;
                default:
                    System.out.println("Ошибка!\nВведите команду правильно");
            }
        }
    }
}


