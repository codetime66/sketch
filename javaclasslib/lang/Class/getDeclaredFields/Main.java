import java.lang.reflect.*;

class Main {
    static void printFields(Field[] fields) {
        for (int i=0; i<fields.length; i++) {
            System.out.println(fields[i]);
        }
    }

    public static void main(String[] args) {
        printFields(java.awt.Point.class.getDeclaredFields());
                            // public int java.awt.Point.x
                            // public int java.awt.Point.y
                   // private static final long java.awt.Point.serialVersionUID

        printFields(C.class.getDeclaredFields());
                            // float C.f
                            // public int C.ci
                            // public int C.cj

        printFields(D.class.getDeclaredFields());
                            // float D.g
                            // public int D.ci
                            // public int D.x

        printFields(I.class.getDeclaredFields());
                            // public static final int I.X
    }
}

class C implements I {
    float f;
    public int ci;
    public int cj;
}

class D extends C {
    float g;
    public int ci;
    public int x;
}

interface I {
    int X = 99;
}
