package day3.collectionFramework;

import java.util.LinkedList;
import java.util.Queue;

public class queueImplement {
    static void main() {
         Queue<Integer> que=new LinkedList<>();
         que.offer(1);
         que.offer(2);
         que.offer(3);
         que.offer(4);
         que.offer(5);
         que.offer(6);
         que.offer(7);

        System.out.println(que);
        System.out.println(que.poll());
        System.out.println(que);
        que.offer(1);
        System.out.println(que);
    }
}
