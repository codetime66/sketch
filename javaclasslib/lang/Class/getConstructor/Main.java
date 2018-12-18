import java.lang.reflect.*;

class Main {
    static void printConstructor(Class c, Class[] paramTypes) {
        try {
            System.out.println(c.getConstructor(paramTypes));
        } catch (NoSuchMethodException e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        printConstructor(String.class, new Class[]{});
                                  // public java.lang.String()

        printConstructor(String.class, new Class[]{String.class});
                                  // public java.lang.String(java.lang.String)

        printConstructor(String.class, new Class[]{char[].class});
                                  // public java.lang.String(char[])

        printConstructor(String.class, new Class[]{char[].class, int.class, 
            int.class});
                                  // public java.lang.String(char[],int,int)

        printConstructor(String.class, new Class[]{char[].class, int.class, 
            int.class});
                                  // public java.lang.String(char[],int,int)

        printConstructor(Main.InnerC.class, new Class[]{});
                                  // public Main$InnerC()

        printConstructor(Main.InnerC.class, new Class[]{int.class});
                                  // NoSuchMethodException
    }

    static class InnerC {
        public InnerC() {}
        InnerC(int i) {}
    }
}
