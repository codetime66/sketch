import java.lang.reflect.Array;

class Main {
    public static void main(String[] args) {
        System.out.println(String.class.getName());       // java.lang.String
        System.out.println(Cloneable.class.getName());    
                                            // java.lang.Cloneable
        // Inner classes and interfaces.
        System.out.println(Main.InnerC.class.getName());  // Main$InnerC
        System.out.println(Main.InnerI.class.getName());  // Main$InnerC

        // Primitive types.
        System.out.println(int.class.getName());          // int
        System.out.println(Void.TYPE.getName());          // void

        // Arrays
        System.out.println(String[].class.getName());     
                                            // [Ljava.lang.String;
        System.out.println(Main.InnerC[].class.getName());// [LMain$InnerC;
        System.out.println(Main.InnerI[].class.getName());// [LMain$InnerI;
        System.out.println(int[].class.getName());        // [I
    }

    static class InnerC {}
    interface InnerI {}
}
