package day5.GenericClassAndMethod;


class Printing<T>{
    T value;

    void set(T value){
        this.value=value;
    }
    T get(){
        return value;
    }
}

class OnlyGenericMethodClass{

    <T> void genericmethodprint(){
        System.out.println("Generic method called..");
    }

    <T> T genericMethodWithValue(T value){
        return value;
    }
}
public class GenericClassMethodImplement {
    static void main() {
        Printing<Integer> intprint=new Printing<>();
        intprint.set(50);
        System.out.println(intprint.get());

        Printing<String> stringprint=new Printing<>();
        stringprint.set("Rishabh");
        System.out.println(stringprint.get());

        Printing<Float> floatprint=new Printing<>();
        floatprint.set(50.45f);
        System.out.println(floatprint.get());

        OnlyGenericMethodClass gm1=new OnlyGenericMethodClass();
        gm1.genericmethodprint();
        System.out.println(gm1.genericMethodWithValue(154));
        System.out.println(gm1.genericMethodWithValue("Hello Rishabh"));
    }
}
