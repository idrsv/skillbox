public class BankAccount {

  private double balance;

  public double getAmount() {
    return balance;
  }

  public void put(double amountToPut) {

    if (amountToPut < 0.0){
      System.out.println("Вы пытаетесь внести на счет отрицательную сумму");
    }
    else {
      balance += amountToPut;
      System.out.println("Выполнено!");
    }
  }

  public void take(double amountToTake) {
    if (amountToTake > balance){
      System.out.println("Вы пытаетесь снять сумму больше,чем есть на вашем счете");
    }
    else {
      balance -= amountToTake;
      System.out.println("Выполнено!");
    }
  }
}
