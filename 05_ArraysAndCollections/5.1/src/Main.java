import java.util.Arrays;
public class Main {
    static double min = 32;
    static double max = 40;
    static double minHealthyPatients = 36.2;
    static double maxHealthyPatients = 36.9;
    static double value;

    public static void main(String[] args) {
//// Код, который меняет порядок расположения элементов внутри массива на обратный.
        String text = "Каждый охотник желает знать, где сидит фазан";
        String[] words1 = text.split(",?\\s+");
        String words2;
        for (int i=0; i < words1.length; i++) {
            System.out.println(words1[i]); }
        int a = words1.length;

        for(int i = 0; i < a / 2; i++)
        {
            words2 = words1[a - i - 1];
            words1[a - i - 1] = words1[i];
            words1[i] = words2; }
        for (int i=0; i < words1.length; i++) {
            System.out.println(words1[i]); }

// Код, который выводит количество пациентов, их максимальную и минимальную температуру и так же для здоровых пациентов.
        double[] temperaturePatients = new double[30];
        for (int i = 0; temperaturePatients.length > i; i++) {
            value =  (Math.random() * (max - min) + min);
            temperaturePatients[i] = value;
            System.out.println("Пациент " + i + " с температурой " + String.format("%.1f", value).replace(',', '.'));
        }
        System.out.println("Средняя температура по больнице " +  String.format("%.1f", averageTemperature(temperaturePatients)).replace(',', '.'));
        System.out.println("Количество здоровых пациентов " + getHealthyPatientsCount(temperaturePatients));
    }

    public static double averageTemperature(double[] temperaturePatients) {
        double averageTemp = 0;
        for (int i = 0; temperaturePatients.length > i; i++) {
            averageTemp += value; }
        averageTemp = averageTemp/temperaturePatients.length;
        return averageTemp;
    }

    public static int getHealthyPatientsCount(double[] temperaturePatients) {
        int healthyCount = 0;
        for (int i = 0; temperaturePatients.length > i; i++) {
        if (minHealthyPatients <= temperaturePatients[i] && temperaturePatients[i] <= maxHealthyPatients)
            healthyCount++;}
        return healthyCount;
    }
}

