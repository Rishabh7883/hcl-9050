package day4;

public class Student implements Comparable<Student>{
    String name;
    int rollno;
    int age;
    int marks;

    public  Student(String name,int rollno,int age,int marks){
        this.name=name;
        this.rollno=rollno;
        this.age=age;
        this.marks=marks;
    }

    @Override
    public String toString() {
        return "User{name='" + name + "', rollno=" + rollno + ", age=" + age + ", marks=" + marks + "}";
    }

    @Override
    public int compareTo(Student o) {
        return Integer.compare(this.age, o.age);
    }
}
