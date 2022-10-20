import java.util.Comparator;
import java.util.List;

public class Main {

    public static final String STAFF_TXT = "data/staff.txt";

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);
        sortBySalaryAndAlphabet(staff);
        System.out.println(staff);
    }

    public static void sortBySalaryAndAlphabet(List<Employee> staff) {
        Comparator<Employee> employeeSalaryAndAlphabetComparator = Comparator.comparingInt(Employee::getSalary).thenComparing(Employee::getName);
        staff.sort(employeeSalaryAndAlphabetComparator);

//        staff.sort((o1, o2) -> {
//            int i = o1.getSalary().compareTo(o2.getSalary());
//            return i != 0 ? i : o1.getName().compareTo(o2.getName());
//        });
    }
}