import java.io.*;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter site address:");
        String url = scanner.nextLine();
        System.out.println("Enter number of threads:");
        int threads = scanner.nextInt();
        LinkMapper linkMapper = new LinkMapper(url);
        String map;
        if (threads == 0) {
            map = new ForkJoinPool().invoke(linkMapper);
        } else {
            map = new ForkJoinPool(threads).invoke(linkMapper);
        }
        writeFile(map);
    }

    private static void writeFile(String map) {
        String filePath = "C:\\Users\\ksit\\java_basics\\13_Multithreading\\ForkJoin\\src\\main\\resources\\SiteMap.txt";
        File file = new File(filePath);
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.write(map);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
