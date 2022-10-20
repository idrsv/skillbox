public class Operator implements Employee{
    Company company;
    private final int salary;

    public Operator(int salary){
        this.salary = salary;
    }

    @Override
    public int getMonthSalary() {
        return salary;
    }

    @Override
    public void setCompany(Company company) {
        this.company = company;
    }
}
