import java.lang.reflect.*;

class Main {
    Main() throws IllegalArgumentException {
        throw new IllegalArgumentException("not implemented yet");
    }
    static void m() throws IllegalArgumentException {
        throw new IllegalArgumentException("out to lunch");
    }

    public static void main(String[] args) {
        try {
            // Create the constructor object and create a new object.
            try {
                Constructor con = 
                    Main.class.getDeclaredConstructor(new Class[] {});
                Object o = con.newInstance(new Object[] {});
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.getTargetException().printStackTrace();
            }

            // Create and invoke the method object.
            try {
                Method m = Main.class.getDeclaredMethod("m", new Class[] {});
                Object o = m.invoke(null, new Object[] {});
            } catch (InvocationTargetException e) {
                e.getTargetException().printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
