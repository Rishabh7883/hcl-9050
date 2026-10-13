package day5.lambdaFunction;

import java.util.function.*;

public class PredefinedFunctionalInterface {
    static void main() {
        Predicate<Integer> isEven = n-> n%2==0;

        System.out.println("Using Predicate For even and odd: ");
        System.out.println(isEven.test(10));
        System.out.println(isEven.test(7));

        Consumer<String> print = name-> System.out.println("Hello "+name);

        System.out.println("Using Consumer For printing value: ");
        print.accept("Rishabh");
        print.accept("Ankit");

        Supplier<String> message = () -> "Supplier Called..";

        System.out.println("Using Suppplier For return a string: ");
        System.out.println(message.get());

        Function<Integer, Integer> square = n ->n*n;

        System.out.println("Using Function For calculating: ");
        System.out.println(square.apply(5));

        BiFunction<Integer,Integer,Integer> add =(a,b)->a+b;

        System.out.println("Using ByFunction For adding two number: ");
        System.out.println(add.apply(10, 20));
    }
}
