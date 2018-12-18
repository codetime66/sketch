import java.lang.reflect.*;

class Main {
    public static void main(String[] args) {
        try {
            Constructor c0 = C.class.getConstructor(new Class[] {});
            Constructor c1 = C.class.getConstructor(new Class[] {});

            System.out.println("c0 == c1:      " + (c0==c1));        // false
            System.out.println("c0.equals(c1): " + (c0.equals(c1))); // true
        } catch (NoSuchMethodException ext) {
            ext.printStackTrace();
        }
    }
}

class C {
    public C() {}
}
