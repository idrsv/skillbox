import java.io.FileOutputStream;

public class DefaultGeneration {
    static final char[] LETTERS = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
    static final int NUMBERS_SIZE = 1000;
    static final int REGION = 100;
    static final String PATH = "res/DefaultGeneration.txt";

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();

        FileOutputStream writer = new FileOutputStream(PATH);

        for (int number = 1; number < NUMBERS_SIZE; number++) {
            for (char firstLetter : LETTERS) {
                for (char secondLetter : LETTERS) {
                    for (char thirdLetter : LETTERS) {
                        String carNumber = firstLetter + padNumber(number, 3) +
                                secondLetter + thirdLetter + padNumber(REGION, 2);
                        writer.write(carNumber.getBytes());
                        writer.write('\n');
                    }
                }
            }
        }
        writer.flush();
        writer.close();

        System.out.println((System.currentTimeMillis() - start) + " ms");
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