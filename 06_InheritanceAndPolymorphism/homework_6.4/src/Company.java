import java.util.*;

public class Company {
    public int income;
    Company company;

    public void setIncome(int income){
        company.income = income;
    }

    public int getIncome(){
        return income;
    }

    private final List<Employee> employeeList = new ArrayList<>();

    private List<Employee> getSortedEmployeeList(int count, Comparator<Employee> comparator ) {
        List<Employee> copyEmployeeList = new ArrayList<>(employeeList);
        copyEmployeeList.sort(comparator);
        List<Employee> resultEmployeeList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            resultEmployeeList.add(copyEmployeeList.get(i));
        }
        return resultEmployeeList;
    }

    public List<Employee> getTopSalaryStaff(int count) {
        return getSortedEmployeeList(count, (o1, o2) -> o2.getMonthSalary() - o1.getMonthSalary());
    }

    public List<Employee> getLowestSalaryStaff(int count) {
        return getSortedEmployeeList(count, (o1, o2) -> o1.getMonthSalary() - o2.getMonthSalary());
    }

    public void hire(Employee employee) {
        employee.setCompany(this);
        this.employeeList.add(employee);
    }

    public void fire(Employee employee) {
        this.employeeList.remove(employee);
    }

    public void hireAll(Collection<Employee> employee){
        this.employeeList.addAll(employee);
    }

    public int countEmployee(){
        return employeeList.size();
    }

    public List<Employee> getEmployeeList(){
        return employeeList;
    }
}