import java.lang.reflect.Array;

class Main {
    public static void main(String[] args) {
        System.out.println(String.class.toString());        
                                        // class java.lang.String
        System.out.println(Cloneable.class.toString());     
                                        // interface java.lang.Cloneable

        // Inner classes and interfaces.
        System.out.println(Main.InnerC.class.toString());   
                                        // class Main$InnerC
        System.out.println(Main.InnerI.class.toString());   
                                        // interface Main$InnerC

        // Primitive types.
        System.out.println(int.class.toString());           // int
        System.out.println(Void.TYPE.toString());           // void

        // Arrays
        System.out.println(String[].class.toString());      
                                        // class [Ljava.lang.String;
        System.out.println(Main.InnerC[].class.toString()); 
                                        // class [LMain$InnerC;
        System.out.println(Main.InnerI[].class.toString()); 
                                        // class [LMain$InnerI;
        System.out.println(int[].class.toString());         
                                        // class [I
    }

    static class InnerC {}
    interface InnerI {}
}
