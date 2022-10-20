import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {

        while (true) {
            EmailList emailList = new EmailList();
            emailList.add("idrisovds@gmail.com");
            emailList.add("savchinvm@gmail.com");
            emailList.add("bogatirevmn@gmail.com");

            Scanner scanner = new Scanner(System.in);

            String input = scanner.nextLine() + " ";

            String command = input.substring(0, input.indexOf(" ")).toUpperCase().trim();

            if (command.startsWith("LIST")) {
                emailList.list();}
            else {
                String email = command.substring(input.indexOf(" "));
                emailList.add(email);}
        }
    }
}
class EmailList {
    TreeSet<String> emailList = new TreeSet<>();

    public void list() {
        System.out.println("Список адресов электронной почты: ");
        for (String email : emailList) {
            System.out.println(email); }
    }
    public void add(String email) {
        emailList.add(email);
    }
}