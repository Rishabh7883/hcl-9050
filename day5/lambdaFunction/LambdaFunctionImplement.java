package day5.lambdaFunction;

@FunctionalInterface
interface MyInterf{
    void show();
}

@FunctionalInterface
interface ArithmaticOpr{
    int calculate(int a,int b);
}

public class LambdaFunctionImplement {
    static void main() {
       MyInterf m1= ()->{
           System.out.println("Functional Interface method called..");
           System.out.println("Lambda expression implemented");
       };

       m1.show();

       ArithmaticOpr addition=(x,y)-> x+y;
       ArithmaticOpr substraction=(x,y)-> x-y;
       ArithmaticOpr multiply=(x,y)-> x*y;
       ArithmaticOpr divide=(x,y)-> x/y;

        System.out.println("Addition: "+addition.calculate(10,5));
        System.out.println("Substraction: "+substraction.calculate(10,5));
        System.out.println("Multiply: "+multiply.calculate(10,5));
        System.out.println("Divide: "+divide.calculate(10,5));

    }
}
