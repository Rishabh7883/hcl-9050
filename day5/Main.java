package day5;

import java.util.*;

class EmployeeSort implements Comparator<Employee> {

    @Override
    public int compare(Employee o1, Employee o2) {
        return o1.age-o2.age;
    }
}
public class Main {
    static void main() {
        Employee e1=new Employee("Lakshya",21,50000);
        Employee e2=new Employee("Ankit",15,10000);
        Employee e3=new Employee("Amit",35,45000);
        Employee e4=new Employee("Ritika",19,30000);


        ArrayList<Employee> employees=new ArrayList<>();
        employees.add(e1);
        employees.add(e2);
        employees.add(e3);
        employees.add(e4);

        // CREATE
        System.out.println("CREATE:");
        employees.forEach(System.out::println);

        // READ
        System.out.println("READ using Iterator:");
        Iterator<Employee> it = employees.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }

        // UPDATE
        for (Employee e : employees) {
            if (e.name.equals("Amit")) {
                e.salary = 65000;
            }
        }
        System.out.println("UPDATE:");
        employees.forEach(System.out::println);

        // DELETE
        Iterator<Employee> itr = employees.iterator();

        while (itr.hasNext()) {
            if (itr.next().name.equals("Ankit")) {
                itr.remove();
            }
        }
        System.out.println("DELETE:");
        employees.forEach(System.out::println);

        // Increasing Traversal
        System.out.println("Increasing Traversal:");
        ListIterator<Employee> listItr = employees.listIterator();

        while (listItr.hasNext()) {
            System.out.println(listItr.next());
        }

        // Decreasing Traversal
        System.out.println("Decreasing Traversal:");

        while (listItr.hasPrevious()) {
            System.out.println(listItr.previous());
        }

        Collections.sort(employees, new EmployeeSort());

        System.out.println("AFTER SORTING BY AGE:");
        employees.forEach(System.out::println);

        System.out.println("AFTER SORTING BY SALARY:");
        Collections.sort(employees, (em1, em2) -> em1.salary - em2.salary);
        employees.forEach(System.out::println);

    }
}
