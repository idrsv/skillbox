public abstract class Client {
    public double balance;
    public double getAmount(){
        return this.balance;
    }

    public void take(double amount){
        double amountWithCommission = amount + getWithdrawCommission(amount);
        this.balance -= amountWithCommission;
    }
    public void put(double amount){
        double amountWithCommission = amount + getReplenishCommission(amount);
        this.balance += amountWithCommission;
    }
    protected abstract double getWithdrawCommission(double amount);

    protected abstract double getReplenishCommission(double amount);
}