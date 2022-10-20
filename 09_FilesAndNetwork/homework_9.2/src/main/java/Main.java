import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        for(;;){
            System.out.println("Задайте путь к папке, которую хотите скопировать: ");
            Scanner scanner = new Scanner(System.in);
            String sourceDirectory = scanner.nextLine().trim();
            System.out.println("Куда хотите скопровать: ");
            String destinationDDirectory = scanner.nextLine().trim();
            FileUtils.copyFolder(sourceDirectory,destinationDDirectory);
            System.out.println("Папка " + sourceDirectory + "\n" + "Скопирована в " + destinationDDirectory);
        }
    }
}
