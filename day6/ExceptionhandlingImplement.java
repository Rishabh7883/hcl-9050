package day6;

class InvalidAgeException extends Exception {
    InvalidAgeException(String message) {
        super(message);
    }
}


public class ExceptionhandlingImplement {
    static void checkAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Age must be 18 or above");
        }
        System.out.println("Eligible");
    }
    static void main() {
        int a=5;
        int b=0;

        System.out.println("Using Try-Catch Block: ");
        try{
            int[] arr=new int[5];
            int sum=a+b;
            int divide=a/b;
            System.out.println(sum+" "+divide);
            System.out.println(arr[5]);
        }catch (ArithmeticException e){
            System.out.println(e.getMessage());
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }

        System.out.println("Using throw Keyword: ");
        int age = 16;
        try {
            if (age < 18) {
                throw new IllegalArgumentException("Age must be 18 or above");
            }
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }finally {
            System.out.println("User age is: "+age);
        }

        System.out.println("Using Custom Exception class: ");
        try {
            checkAge(16);
            checkAge(25);
        } catch (InvalidAgeException e) {
            System.out.println(e.getMessage());
        }

    }
}
