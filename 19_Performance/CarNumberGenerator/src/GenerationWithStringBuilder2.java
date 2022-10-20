import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class GenerationWithStringBuilder2 {

    static final char[] LETTERS = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
    static final int NUMBERS_SIZE = 1000;
    static final int REGION = 100;
    static final String PATH = "res/GenerationWithStringBuilder2.txt";

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        try (PrintWriter writer = new PrintWriter(PATH)) {
            StringBuilder builder = new StringBuilder();
            for (int number = 1; number < NUMBERS_SIZE; number++) {
                for (char firstLetter : LETTERS) {
                    for (char secondLetter : LETTERS) {
                        for (char thirdLetter : LETTERS) {
                            builder.append(firstLetter);
                            builder.append(padNumber(number, 3));
                            builder.append(secondLetter);
                            builder.append(thirdLetter);
                            builder.append(padNumber(REGION, 2));
                            builder.append("\n");
                        }
                    }
                }
            }
            writer.write(builder.toString());
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println((System.currentTimeMillis() - start) + " ms");
    }

    private static String padNumber(int number, int numberLength) {
        StringBuilder numberStr = new StringBuilder(Integer.toString(number));
        int padSize = numberLength - numberStr.length();
        for (int i = 0; i < padSize; i++) {
            numberStr.insert(0, '0');
        }
        return numberStr.toString();
    }
}
