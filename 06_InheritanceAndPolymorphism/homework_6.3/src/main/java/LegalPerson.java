public class LegalPerson extends PhysicalPerson {

    @Override
    protected double getWithdrawCommission(double amount) {
        return amount * 0.01;
    }
}