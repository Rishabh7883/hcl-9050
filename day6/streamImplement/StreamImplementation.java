package day6.streamImplement;

import java.util.*;
import java.util.stream.Collectors;

class PrintValue{
    public static void output(int val){
        System.out.print(val+" ");
    }
}
public class StreamImplementation {
    static void main() {
        ArrayList<Integer> intlist= new ArrayList<>(Arrays.asList(10, 25, 30, 15, 40, 5));
        ArrayList<String> strlist=new ArrayList<>(Arrays.asList("Rishabh","Ankit","Ritika","Shashank","Amit","Shalini"));
        Map<String,String> nameinfo=new HashMap<>();
        nameinfo.put("Rishabh","male");
        nameinfo.put("Ankit","male");
        nameinfo.put("Ritika","female");
        nameinfo.put("Shashank","male");
        nameinfo.put("Amit","male");
        nameinfo.put("Shalini","female");

        System.out.println("Even numbers:");
        intlist.stream().filter(n->n%2==0).forEach(PrintValue::output);
        System.out.println();

        System.out.println("Squares:");
        intlist.stream().map(n->n*n).forEach(PrintValue::output);
        System.out.println();

        List<Integer> evenList = intlist.stream().filter(n->n%2==0).collect(Collectors.toList());
        System.out.println("Evenlist is: "+evenList);

        System.out.println("Count Names:");
        long countname = strlist.stream().filter(n->n.startsWith("A")).count();
        List<String> aNameList = strlist.stream().filter(n->n.startsWith("A") && n.endsWith("t")).collect(Collectors.toList());
        System.out.println(countname);
        System.out.println("A Name List:");
        System.out.println(aNameList);

        System.out.println("Adding Mr and Ms:");
        List<String> updatednames=strlist.stream().map(name->{
            if(nameinfo.get(name).equals("female")) return "Ms. "+name;
            else return "Mr. "+name;
        }).collect(Collectors.toList());
        System.out.println(updatednames);
    }
}
