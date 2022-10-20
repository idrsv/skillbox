public class CardAccount extends BankAccount {
    // Реализация класса карточного счета
    @Override
    public void take(double amountToTake) {
        double percentToTake = amountToTake * 0.01;
        super.take(amountToTake + percentToTake);
        System.out.println("Выполнено!");
    }
}
