public class Manager implements Employee{
    Company company;
    private final int salary;

    public Manager(int salary){
        int min = 115000;
        int max = 140000;
        int percent = (int) (0.05 * (int) (Math.random() * (max - min) + min));
        this.salary = salary + percent;
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
