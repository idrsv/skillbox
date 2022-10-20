import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        for(;;){
            System.out.println("Задайте путь к папке: " + "\n Например: C:/Users/ksit/java_basics");
            Scanner scanner = new Scanner(System.in);
            String sourceFile = scanner.nextLine().trim();
            System.out.println("Размер папки " + sourceFile + " составляет " +
                    FileUtils.calculateFolderSize(sourceFile) + " байт");
        }
    }
}
