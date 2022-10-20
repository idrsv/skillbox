import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Bank {

    private Map<String, Account> accounts;
    private final Random random = new Random();

//    public Bank(HashMap<String, Account> accounts) {
//        this.accounts = accounts;
//    }

    public boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
            throws InterruptedException {
        Thread.sleep(1);
        return random.nextBoolean();
    }


    public void transfer(Map<String, Account> accounts, String fromAccountNum, String toAccountNum, long amount) {
        Account sourceAccount = accounts.get(fromAccountNum);
        Account targetAccount = accounts.get(toAccountNum);
        long moneySource = accounts.get(fromAccountNum).getMoney();
        long moneyTarget = accounts.get(toAccountNum).getMoney();
        boolean isBlocked = false;

        if (amount <= 0) {
            System.out.println("Сумма перевода не может быть отрицательной!");
        } else if (amount > 50000) {
            try {
                if (isFraud(fromAccountNum, toAccountNum, amount)) {
                    accounts.get(fromAccountNum).setBlocked(true);
                    accounts.get(toAccountNum).setBlocked(true);
                    isBlocked = true;
                    System.out.println("\nБанк заблокировал аккаунт №" + fromAccountNum + " и аккаунт №" + toAccountNum + " Идет проверка в службе безопасности...");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (amount > moneySource) {
            System.out.println("\nНедостаточно денег для перевода с аккаунта №" + fromAccountNum + " на аккаунт №" + toAccountNum
                    + " \n\tБаланс аккаунта: №" + fromAccountNum + " составляет " + accounts.get(fromAccountNum).getMoney()
                    + " \n\tБаланс аккаунта: №" + toAccountNum + " составляет " + accounts.get(toAccountNum).getMoney()
                    + " \n\t\tСумма отклоненной операции составила: " + amount);
        } else if (moneySource >= amount && !isBlocked) {
            if (Integer.parseInt(sourceAccount.getAccountNumber()) > Integer.parseInt(targetAccount.getAccountNumber())) {
                synchronized (sourceAccount) {
                    synchronized (targetAccount) {
                        accounts.get(fromAccountNum).setMoney(moneySource - amount);
                        accounts.get(toAccountNum).setMoney(moneyTarget + amount);
                        System.out.println("\nПеревод суммы: " + amount + " с аккаунта №" + fromAccountNum + " на аккаунт №" + toAccountNum + " выполнен."
                                + " \n\tБаланс аккаунта: №" + fromAccountNum + " составляет " + accounts.get(fromAccountNum).getMoney()
                                + " \n\tБаланс аккаунта: №" + toAccountNum + " составляет " + accounts.get(toAccountNum).getMoney()
                                + " \n\t\tСумма перевода составила: " + amount);
                    }
                }
            }
        } else {
            synchronized (targetAccount) {
                synchronized (sourceAccount) {
                    accounts.get(fromAccountNum).setMoney(moneySource - amount);
                    accounts.get(toAccountNum).setMoney(moneyTarget + amount);
                    System.out.println("\nПеревод суммы: " + amount + " с аккаунта №" + fromAccountNum + " на аккаунт №" + toAccountNum + " выполнен."
                            + " \n\tБаланс аккаунта: №" + fromAccountNum + " составляет " + accounts.get(fromAccountNum).getMoney()
                            + " \n\tБаланс аккаунта: №" + toAccountNum + " составляет " + accounts.get(toAccountNum).getMoney()
                            + " \n\t\tСумма перевода составила: " + amount);
                }
            }
        }
    }

    public void createRandomTransfers(Bank bank, Map accounts, int transfersNumber) {
        for (int i = 0; i < transfersNumber; i++) {
            String accFrom = ("" + (int) (Math.random() * 1000));
            String accTo = ("" + (int) (Math.random() * 1000));
            long amountRnd = (long) (Math.random() * 75000 + 1);
            synchronized (accTo) {
                synchronized (accFrom) {
                    bank.transfer(accounts, accFrom, accTo, amountRnd);
                }
            }
        }
    }

    public HashMap createAccountCollection(int accountsQuantity) {
        HashMap<String, Account> bankAccounts = new HashMap<>();
        for (int i = 0; i < accountsQuantity; i++) {
            Account account = new Account(Integer.toString(i), (long) (Math.random() * 100000), false);
            bankAccounts.put(Integer.toString(i), account);
        }
        return bankAccounts;
    }

    public long getBalance(HashMap<String, Account> accounts, String accountNum) {
        long moneyOnAccount = accounts.get(accountNum).getMoney();
        return moneyOnAccount;
    }
}