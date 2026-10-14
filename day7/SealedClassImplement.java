package day7;

sealed class Employee permits Manager, Developer {
    public void display() {
    }
}

final class Manager extends Employee {
    @Override
    public void display() {
        System.out.println("Manager class called....");
    }
}

final class Developer extends Employee {
    @Override
    public void display() {
        System.out.println("Developer class called....");
    }
}

public class SealedClassImplement {
    public static void main(String[] args) {
        Employee m = new Manager();
        Employee d = new Developer();

        m.display();
        d.display();
    }
}