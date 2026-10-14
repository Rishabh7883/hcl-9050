package day7;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class RecordClassImplement {
    record Employee(int id, String name, String dept, BigDecimal salary,
                    LocalDate hireDate, Set<String> skills) {}

    static Employee e(int id, String n, String d, int sal, String date, String... skills) {
        return new Employee(id, n, d, BigDecimal.valueOf(sal),
                LocalDate.parse(date), Set.of(skills));
    }

    public static void main(String[] args) {
        List<Employee> emps = List.of(
                e(1, "Asha","Engineering",90000, "2019-03-01", "Java", "SQL"),
                e(2, "Ravi","Engineering",120000, "2016-07-15", "Java", "Spring", "AWS"),
                e(3, "Meera","HR",60000, "2020-01-10", "Recruiting"),
                e(4, "John","Sales",70000, "2018-11-20", "Negotiation", "CRM"),
                e(5, "Priya","Engineering",105000, "2021-05-05", "Java", "React"),
                e(6, "Karan","Sales",65000, "2022-02-14", "CRM"),
                e(7, "Sara","HR",62000, "2017-09-09", "Recruiting", "Payroll"),
                e(8, "Vikram","Finance",80000, "2015-04-30", "Excel", "SQL"));

        System.out.println("Descending Order Sorting by Salary: ");
        List<String> decendingEmpNames = emps.stream().sorted((e1, e2)->e2.salary().compareTo(e1.salary())).map(e->e.name).collect(Collectors.toList());
        System.out.println(decendingEmpNames);

        System.out.println("Top three earners:");
        List<String> topearners=new ArrayList<>();
        topearners=decendingEmpNames.subList(0,3);
        System.out.println(topearners);

        System.out.println("Employees hired before 2018-01-01:");
        List<String> empBeforeDate = emps.stream()
                .filter(e->e.hireDate().isBefore(LocalDate.parse("2018-01-01")))
                .sorted((year1,year2)->year1.hireDate.compareTo(year2.hireDate))
                .map(e->e.name()+"("+e.hireDate().getYear()+")")
                .toList();

        System.out.println(empBeforeDate);

        System.out.println("Join all names: ");
        List<String> allNames=List.of(emps.stream().map(e->e.name).collect(Collectors.joining(", ")));
        System.out.println(allNames);
    }
}
