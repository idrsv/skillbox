public class Account
{
    private String accountNumber;
    private long money;
    private boolean blocked;

    public Account(String accountNumber, long money, boolean blocked) {
        this.accountNumber = accountNumber;
        this.money = money;
        this.blocked = false;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
}