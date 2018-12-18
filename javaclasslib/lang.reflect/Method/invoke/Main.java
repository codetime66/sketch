import java.lang.reflect.*;
import java.awt.*;

class Main {
    static void invoke(Object o, String name, Class[] params, Object[] args) {
        try {
            Method m = C.class.getDeclaredMethod(name, params);
            System.out.print(name + "() -> ");
            Object r = m.invoke(o, args);
    
            if (r != null) {
                System.out.println(r.getClass().getName()+": "+r);
            } else {
                System.out.println(m.getReturnType().getName()+": "+r);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.getTargetException().printStackTrace();
        }
    }

    public static void main(String[] args) {
        C c = new C();

        // Demonstrate that if return type is primitive, it is wrapped
        // with an Object wrapper.
        invoke(c, "m1", new Class[] {}, new Object[] {});

        // Demonstrate that if parameter is primitive, must wrap with
        // Object wrapper.
        invoke(c, "m2", new Class[] {double.class}, 
               new Object[] {new Double(0)});

        // Demonstrate that an int value can be widened to a double.
        // Also demonstrate how to handle m2's exception.
        invoke(c, "m2", new Class[] {double.class}, 
               new Object[] {new Integer(100)});

        // Demonstrate that Button can be widened to Component.
        invoke(c, "m3", new Class[] {Component.class}, 
               new Object[] {new Button("OK")});

        // Demonstrate that even though the method object refers to C.m1(),
        // D.m1() will be called.
        D d = new D();
        invoke(d, "m1", new Class[] {}, new Object[] {});
    }
}

class C {
    double m1() {
        return 3.14;
    }
    void m2(double d) throws IllegalArgumentException {
        if (d > 50) {
            throw new IllegalArgumentException();
        }
    }
    void m2(int i) {
        System.out.println("*** NOT REACHED ***");
    }
    void m3(Component c) {
    }
}

class D extends C {
    double m1() {
        return 2*super.m1();
    }
}
