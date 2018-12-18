import java.lang.reflect.Field;

class Main {
    static void print(Field f) {
        System.out.println(f);
    }

    public static void main(String[] args) {
        try {
            // print(D.class.getField("f"));    // NoSuchFieldException
            print(D.class.getField("ci"));      // public int D.ci
            // print(D.class.getField("C.ci")); // NoSuchFieldException
            print(D.class.getField("cj"));      // public int C.cj
            print(D.class.getField("X"));       // public static final int I.X
            print(D.class.getField("Y"));       // public static final int J.Y

            print(C.class.getField("ci"));      // public int C.ci
            // print(C.class.getField("Q"));    // NoSuchFieldException
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
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
}

interface I {
    int X = 99;
}

interface J extends I {
    int Y = 99;
}
