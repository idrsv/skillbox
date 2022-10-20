import java.time.LocalDate;
// Реализация класса депозитарного счета
public class DepositAccount extends BankAccount {

    private LocalDate lastIncome;
    private LocalDate nowDate = LocalDate.now();

    @Override
    public void put(double amountToPut) {
        lastIncome = LocalDate.now();
        super.put(amountToPut); }

    @Override
    public void take(double amountToTake) {
        if (lastIncome.isBefore(nowDate)){
            if (lastIncome.isBefore(nowDate.plusMonths(1))){
                if (lastIncome.isBefore(nowDate.plusYears(1)))
                System.out.println("Рано");
            }
        }
        super.take(amountToTake);
    }
}
