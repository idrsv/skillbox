import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final int threadsCount =  10;
    private static final int transferCount = 5;
    private static final int accountsCount = 100;

    public static void main(String[] args) {
        Map<String, Account> bankAccounts = new HashMap<>();
        for (int i = 0; i < accountsCount; i++) {
            Account account = new Account(Integer.toString(i), (long) (Math.random() * 10000), false);
            bankAccounts.put(Integer.toString(i), account);
        }

        Bank bank = new Bank();
        ExecutorService service = Executors.newCachedThreadPool();

        for (int i = 0; i < threadsCount; i++) {
            service.submit(() -> {
                for (int j = 0; j < transferCount; j++) {
                    String accountSource = ("" + (int) (Math.random() * 100));
                    String accountTarget = ("" + (int) (Math.random() * 100));
                    long amountRnd = (long) (Math.random() * 5000);
                    bank.transfer(bankAccounts, accountSource, accountTarget, amountRnd);
                }

            });
        }
        service.shutdown();
    }
}
