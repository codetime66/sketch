import java.lang.reflect.*;

class Main {
    public static void main(String[] args) {
        try {
            Field cx = C.class.getField("x");
            Field cy = C.class.getField("y");
    
            Field dx = D.class.getField("x");
            Field dy = D.class.getField("y");
    
            Field ex = E.class.getField("x");

            checkEquality(cx, dx);    // C.x != D.x
            checkEquality(cy, dy);    // C.y == C.y
            checkEquality(cx, ex);    // C.x != E.x
        } catch (NoSuchFieldException ext) {
            ext.printStackTrace();
        }
    }

    public static void checkEquality(Field f1, Field f2) {
	System.out.print(f1.getDeclaringClass().getName()+"."+f1.getName());
        if (f1.equals(f2)) {
            System.out.print(" == ");
        } else {
            System.out.print(" != ");
        }
	System.out.println(f2.getDeclaringClass().getName()+"."+f2.getName());
    }
}

class C {
    public int x;
    public int y;
}
class D extends C {
    public int x;
}
class E {
    public int x;
}
