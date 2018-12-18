class Main {
    static void printComponentType(Class c) {
        System.out.println(c.getComponentType());
    }

    public static void main(String[] args) {
        // printComponentType(null);            // NullPointerException
        printComponentType(int.class);          // null
        printComponentType(int[].class);        // int
        printComponentType(int[][].class);      // class [I
        printComponentType(String[].class);     // class java.lang.String
    }
}
