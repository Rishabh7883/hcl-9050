package day3.collectionFramework;

import java.util.HashMap;
import java.util.Map;

public class mapImplement {
    static void main() {
        Map<Integer,String> map=new HashMap<>();
        map.put(1,"Rishabh");
        map.put(2,"Ankit");
        map.put(3,"Shashank");
        map.put(4,"Aditya");
        map.put(5,"Abhishek");

        System.out.println(map);

        for(Map.Entry<Integer,String> entry: map.entrySet()){
            System.out.println(entry.getKey()+" "+entry.getValue());
        }

        System.out.println(map.containsKey(5));
    }
}
