public class TopManager implements Employee{
    Company company;
    private final int salary;

    public TopManager(int salary){
        this.salary = salary;
    }

    @Override
    public int getMonthSalary() {
        if (company.getIncome() > 10000000){
            return (int) (2.5 * salary);
        }
        return salary;
    }

    @Override
    public void setCompany(Company company) {
        this.company = company;
    }
}