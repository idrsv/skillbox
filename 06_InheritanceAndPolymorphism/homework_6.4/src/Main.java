public class Main {
    public static void main(String[] args) {
        Company company = new Company();
        company.setIncome(2000000);
        hireEmployees(company);
        System.out.println("Создаем и нанимаем сотрудников: " + company.countEmployee());
        System.out.println("Список из 15 самых высоких зарплат в компании: ");
        printHighSalaries(company);
        System.out.println("Список из 30 самых низких зарплат в компании: ");
        printLowSalaries(company);
        layOffHalfEmployees(company);
        System.out.println("Увольнение сотрудников в компании: " + company.countEmployee());
        System.out.println("Список из 15 самых высоких зарплат в компании: ");
        printHighSalaries(company);
        System.out.println("Список из 30 самых низких зарплат в компании: ");
        printLowSalaries(company); // Список из 30 самых низких зарплат в компании.
    }
    private static void hireEmployees(Company company) {
        for (int i = 0; i < 180; i++) {
            Employee operator = new Operator(40000);
            company.hire(operator);
        }
        for (int i = 0; i < 80; i++) {
            Employee manager = new Manager(80000);
            company.hire(manager);
        }
        for (int i = 0; i < 10; i++) {
            Employee topManager = new TopManager(150000);
            company.hire(topManager);

        }
    }
    private static void printLowSalaries(Company company){
        for (Employee employee : company.getLowestSalaryStaff(30)) {
            System.out.println(employee.getMonthSalary());
        }
    }

    private static void printHighSalaries(Company company){
        for (Employee employee : company.getTopSalaryStaff(15)) {
            System.out.println(employee.getMonthSalary());
        }
    }

    private static void layOffHalfEmployees(Company company){
        int halfCountEmployee = (company.countEmployee())/2;
        for (int i = 0; i < halfCountEmployee; i++){
            int random = (int) (Math.random() * company.countEmployee());
            Employee halfEmployee = company.getEmployeeList().get(random);
            company.fire(halfEmployee);
        }
    }
}
