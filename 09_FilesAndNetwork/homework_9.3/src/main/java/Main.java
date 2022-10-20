public class Main {

    private static final String pathMovementsCsv = "C:\\Users\\maksi\\java_basics\\09_FilesAndNetwork\\homework_9.3\\src\\test\\resources\\movementList.csv";

    public static void main(String[] args) {
        Movements movements = new Movements(pathMovementsCsv);
        System.out.println("Доходы компании " + movements.getIncomeSum());
        System.out.println("Расходы компании " + movements.getExpenseSum());
    }
}
