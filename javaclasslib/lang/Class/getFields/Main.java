import java.lang.reflect.*;

class Main {
    static void printFields(Field[] fields) {
        for (int i=0; i<fields.length; i++) {
            System.out.println(fields[i]);
        }
    }

    public static void main(String[] args) {
        printFields(java.awt.Point.class.getFields());
                            // public int java.awt.Point.x
                            // public int java.awt.Point.y

        printFields(C.class.getFields());
                            // public static final int I.X
                            // public static final int J.Y
                            // public int C.ci
                            // public int C.cj

        printFields(D.class.getFields());
                            // public static final int I.X
                            // public static final int J.Y
                            // public int C.ci
                            // public int C.cj
                            // public int D.ci
                            // public int D.x

        printFields(I.class.getFields());
                            // public static final int I.X

        printFields(J.class.getFields());
                            // public static final int I.X
                            // public static final int J.Y
    }
}

class C implements J {
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

interface J extends I {
    int Y = 99;
}
