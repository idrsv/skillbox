import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class ThreadsGeneration {

    static void threadsGenerate(int numbers ,int region, char[] letters, String path) {
        try (PrintWriter writer = new PrintWriter(path)) {
            StringBuilder builder = new StringBuilder();
            for (int number = 1; number < numbers; number++) {
                for (char firstLetter : letters) {
                    for (char secondLetter : letters) {
                        for (char thirdLetter : letters) {
                            builder.append(firstLetter);
                            builder.append(padNumber(number, 3));
                            builder.append(secondLetter);
                            builder.append(thirdLetter);
                            builder.append(padNumber(region, 2));
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
    }

    private static String padNumber(int number, int numberLength) {
        String numberStr = Integer.toString(number);
        int padSize = numberLength - numberStr.length();
        for (int i = 0; i < padSize; i++) {
            numberStr = '0' + numberStr;
        }
        return numberStr;
    }
}
