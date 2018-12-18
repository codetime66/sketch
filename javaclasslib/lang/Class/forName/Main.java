class Main {
    public static void main(String[] args) {
        try {
            System.out.println(Class.forName("java.lang.String"));// class java.lang.String
            System.out.println(Class.forName("java.lang.Cloneable"));
                                                        // interface java.lang.Cloneable
            //System.out.println(Class.forName("String"));        // ClassNotFoundException

            // Inner classes and interfaces.
            System.out.println(Class.forName("Main$InnerC"));     // class Main$InnerC
            System.out.println(Class.forName("Main$InnerI"));     // interface Main$InnerI

            // Primitive types.
            //System.out.println(Class.forName("int"));           // ClassNotFoundException
            //System.out.println(Class.forName("I"));             // ClassNotFoundException

            // Arrays
            System.out.println(Class.forName("[I"));              // class [I
            System.out.println(Class.forName("[[I"));             // class [[I
            System.out.println(Class.forName("[Ljava.lang.String;"));// class [Ljava.lang.String;
            System.out.println(Class.forName("[LMain$InnerC;"));   // class [LMain$InnerC;
            System.out.println(Class.forName("[LMain$InnerI;"));   // class [LMain$InnerI;

            // A roundabout way of getting the Class object for a primitive type.
            System.out.println(Class.forName("[I").getComponentType());  // int
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class InnerC {}
    interface InnerI {}
}
