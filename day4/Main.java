package day4;

import java.util.ArrayList;
import java.util.Collections;

public class Main {
    static void main() {
        Student s1=new Student("Rishabh",207,21,90);
        Student s2=new Student("Ankit",320,22,91);
        Student s3=new Student("Shashank",210,19,85);
        Student s4=new Student("Aditya",200,18,90);
        Student s5=new Student("Abhishek",133,15,50);

        ArrayList<Student> al=new ArrayList<>();
        al.add(s1);
        al.add(s2);
        al.add(s3);
        al.add(s4);
        al.add(s5);

        System.out.println(al);

        Collections.sort(al);
        System.out.println(al);

        Collections.sort(al,(a,b)->{
            return Integer.compare(b.marks,a.marks);
        });

        System.out.println(al);
    }
}
