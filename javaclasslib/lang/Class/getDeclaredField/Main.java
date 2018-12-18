import java.lang.reflect.Field;

class Main {
    static void print(Field f) {
        System.out.println(f);
    }

    public static void main(String[] args) {
        try {
            print(D.class.getDeclaredField("g"));        // public int D.ci
            print(D.class.getDeclaredField("ci"));       // public int D.ci

            // print(D.class.getDeclaredField("D.g"));   // NoSuchFieldException
            // print(D.class.getDeclaredField("C.ci"));  // NoSuchFieldException
            // print(D.class.getDeclaredField("X"));     // NoSuchFieldException
            // print(D.class.getDeclaredField("Q"));     // NoSuchFieldException
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}

class C {
    public int ci;
    public int cj;
}

class D extends C implements I {
    float g;
    public int ci;
}

interface I {
    int X = 99;
}
