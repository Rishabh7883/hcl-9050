package day3.collectionFramework;

import java.util.ArrayDeque;
import java.util.Deque;

public class stackImplement {
    static void main() {
        Deque<Integer> stack=new ArrayDeque<>();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        System.out.println(stack);
        stack.pop();
        stack.pop();
        System.out.println(stack);
    }
}
