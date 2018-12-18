import java.lang.reflect.*;

class Main {
    static void printConstructor(Class c, Class[] paramTypes) {
        try {
            System.out.println(c.getDeclaredConstructor(paramTypes));
        } catch (NoSuchMethodException e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        printConstructor(String.class, new Class[]{});
                                    // public java.lang.String()

        printConstructor(String.class, 
            new Class[]{int.class, int.class, char[].class});
                                    // private java.lang.String(int,int,char[])

        printConstructor(Main.InnerC.class, new Class[]{});
                                    // public Main$InnerC()

        printConstructor(Main.InnerC.class, new Class[]{int.class});
                                    // Main$InnerC(int)

        printConstructor(Main.InnerC.class, new Class[]{float.class});
                                    // Main$InnerC(float)
    }

    static class InnerC {
        public InnerC() {}
        InnerC(int i) {}
        private InnerC(float i) {}
    }
}
