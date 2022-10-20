import java.util.*;

public class Main {
    static ArrayList<String> list = new ArrayList<>();
    static HashSet<String> hashSet = new HashSet<>();
    static TreeSet<String> treeSet = new TreeSet<>();

    // Z999ZZ199
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        String[] licensePlateLetters = {"A", "B", "C", "E", "H", "K", "M", "O", "P", "T", "X", "Y"};
        StringBuilder str = new StringBuilder("");

            for (int i = 0; i < licensePlateLetters.length; i++) {
                for (int licensePlateNumbers = 111; licensePlateNumbers <= 999; licensePlateNumbers += 111) {
                    for (int j = 0; j < licensePlateLetters.length; j++) {
                        for (int k = 0; k < licensePlateLetters.length; k++) {
                            for (int m = 1; m <= 199; m++) {
                                str.append(licensePlateLetters[i]).append(licensePlateNumbers).append(licensePlateLetters[j]).append(licensePlateLetters[k]).append(m);
                                list.add(str.toString());
                                str.setLength(0);
                            }
                        }
                    }
                }
            }
            hashSet.addAll(list);
            treeSet.addAll(list);
            list.sort(Collections.reverseOrder());

            long start1 = System.nanoTime();
            list.contains(input);
            long duration1 = System.nanoTime() - start1;
            System.out.println("Время поиска по прямому перебору составляет: " + duration1);
            long start2 = System.nanoTime();
            int index = Collections.binarySearch(list, input, Collections.reverseOrder());
            long duration2 = System.nanoTime() - start2;
            System.out.println("Время по бинарному поиску составляет: " + duration2);
            long start3 = System.nanoTime();
            treeSet.contains(input);
            long duration3 = System.nanoTime() - start3;
            System.out.println("Время поиска по TreeSet составляет: " + duration3);
            long start4 = System.nanoTime();
            hashSet.contains(input);
            long duration4 = System.nanoTime() - start4;
            System.out.println("Время поиска по HashSet составляет: " + duration4);
    }
}

//A999BO178 3094848 количество 