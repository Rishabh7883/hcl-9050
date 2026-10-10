package collectionFramework;

import java.util.Collections;
import java.util.PriorityQueue;

public class priorityQueueImplement {
    static void main() {
        PriorityQueue<Integer> minpq=new PriorityQueue<>();
        PriorityQueue<Integer> maxpq = new PriorityQueue<>(Collections.reverseOrder());
        System.out.println("MIN HEAP PQ");
        minpq.add(8);
        minpq.add(5);
        minpq.add(2);
        minpq.add(3);
        minpq.add(4);

        System.out.println(minpq);
        System.out.println(minpq.peek());
        minpq.poll();
        System.out.println(minpq);
        System.out.println(minpq.peek());


        System.out.println("MAX HEAP PQ");
        maxpq.add(8);
        maxpq.add(5);
        maxpq.add(2);
        maxpq.add(3);
        maxpq.add(4);

        System.out.println(maxpq);
        System.out.println(maxpq.peek());
        maxpq.poll();
        System.out.println(maxpq);
        System.out.println(maxpq.peek());


    }
}
