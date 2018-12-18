import java.lang.reflect.*;

class Main {
    public static void main(String[] args) {
        try {
            // Demonstrate that if a class does not define any constructors,
            // a constructor without any parameters is automatically defined.
            Constructor cons = C.class.getDeclaredConstructor(new Class[] {});
            C c = (C)cons.newInstance(new Object[] {});

            // Demonstrate that a value must be wrapped in an object wrapper
            // if the parameter type is primitive.
            cons = D.class.getDeclaredConstructor(new Class[] {int.class});
            D d = (D)cons.newInstance(new Object[] {new Integer(0)});

            // Demonstrate that a byte parameter is automatically 
            // widened to an int.
            d = (D)cons.newInstance(new Object[] {new Byte((byte)0)});
            
            // Demonstrate how to handle an exception thrown by the constructor.
            d = (D)cons.newInstance(new Object[] {new Integer(-1)});
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.getTargetException().printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}

class C {
}
class D {
    D(int i) throws IllegalArgumentException {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
    }
}

