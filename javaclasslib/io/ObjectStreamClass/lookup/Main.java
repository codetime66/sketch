import java.io.ObjectStreamClass;
import java.io.Serializable;

class Main {
    public static void main(String[] args) {
        System.out.println(ObjectStreamClass.lookup(java.lang.String.class));
        System.out.println(ObjectStreamClass.lookup(Class1.class));
        System.out.println(ObjectStreamClass.lookup(Class2.class)); // null
    }
}

class Class1 implements Serializable {
    int field1;
    String field2;
    transient int field3;

    public Class1() {
        field1 = 10;
        field2 = "a string";
        field3 = -1;
    }
}

class Class2 {
    int fieldx;
}
