public class PhysicalPerson extends Client{
    @Override
    public void put(double amount) {
        if (amount < 0.0){
            amount = 0.0;
        }super.put(amount);
    }
    @Override
    public void take(double amount) {
        if (amount > balance){
            amount = 0.0;
        }super.take(amount);
    }

    @Override
    protected double getWithdrawCommission(double amount) {
        return 0;
    }

    @Override
    protected double getReplenishCommission(double amount) {
        return 0;
    }
}