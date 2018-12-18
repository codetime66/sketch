import java.lang.reflect.*;

class Main {
    static void printConstructors(Constructor[] cons) {
        for (int i=0; i<cons.length; i++) {
            System.out.println(cons[i]);
        }
    }
    public static void main(String[] args) {
        printConstructors(StringBuffer.class.getConstructors());
            // public java.lang.StringBuffer()
            // public java.lang.StringBuffer(int)
            // public java.lang.StringBuffer(java.lang.String)

        printConstructors(InnerC.class.getConstructors());
            // public Main$InnerC()

        printConstructors(java.lang.Cloneable.class.getConstructors());

        printConstructors(int.class.getConstructors());
    }

    static class InnerC {
        public InnerC() {}
        InnerC(int i) {}
    }
}
