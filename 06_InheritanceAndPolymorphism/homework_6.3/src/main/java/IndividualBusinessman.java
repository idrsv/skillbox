public class IndividualBusinessman extends PhysicalPerson {

    @Override
    protected double getReplenishCommission(double amount) {
        if (amount >= 1000.0){
            return amount * 0.005;
        }
        else {
            return amount * 0.01;
        }
    }
}