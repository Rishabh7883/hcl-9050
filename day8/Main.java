package day8;

class Example{
    static int count=0;
   static void display(){
       System.out.println("hello");
   }
}
class PrivateImpl{
    private static PrivateImpl obj=new PrivateImpl();
    private int count=0;

    private PrivateImpl(){

    }

    public static PrivateImpl getInstance(){
        return obj;
    }

    public int getCount(){
        return count;
    }
    public void setCount(int count){
        this.count=count;
    }

}
public class Main {
    static void main() {
        Example e1=new Example();
        Example e2=new Example();

        System.out.println(e1.hashCode()+" "+e2.hashCode());

        e1.count =50;
        System.out.println(e2.count);

        PrivateImpl p1=PrivateImpl.getInstance();
        PrivateImpl p2=PrivateImpl.getInstance();

        System.out.println(p1.hashCode()+" "+p2.hashCode());
        p1.setCount(25);
        System.out.println(p1.getCount()+" "+p2.getCount());
    }
}
