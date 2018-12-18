import java.lang.reflect.*;

class Main {
    public static void main(String[] args) {
        try {
            Method cm = C.class.getMethod("m", new Class[] {});
            Method cn = C.class.getMethod("n", new Class[] {});
    
            Method dm = D.class.getMethod("m", new Class[] {});
            Method dn = D.class.getMethod("n", new Class[] {});
    
            Method em = E.class.getMethod("m", new Class[] {});

            checkEquality(cm, dm);    // C.m != D.m
            checkEquality(cn, dn);    // C.n == C.n
            checkEquality(cm, em);    // C.m != E.m
        } catch (NoSuchMethodException ext) {
            ext.printStackTrace();
        }
    }

    public static void checkEquality(Method m1, Method m2) {
        System.out.print(m1.getDeclaringClass().getName()+"."+m1.getName());
        if (m1.equals(m2)) {
            System.out.print(" == ");
        } else {
            System.out.print(" != ");
        }
        System.out.println(m2.getDeclaringClass().getName()+"."+m2.getName());
    }
}

class C {
    public void m() {}
    public void n() {}
}
class D extends C {
    public void m() {}
}
class E {
    public void m() {};
}
