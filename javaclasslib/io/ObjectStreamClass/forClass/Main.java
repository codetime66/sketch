import java.io.ObjectStreamClass;
import java.io.Serializable;

class Main {
    public static void main(String[] args) {
        ObjectStreamClass desc1 = ObjectStreamClass.lookup(Class1.class);

        System.out.println(desc1.forClass());           // class Class1
        System.out.println(desc1.getName());            // Class1
        System.out.println(desc1.getSerialVersionUID());// -7143511112820278689
    }
}
