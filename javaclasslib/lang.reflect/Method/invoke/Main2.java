import java.lang.reflect.*;
import java.util.*;

class Main2 {
    public static void main(String[] args) throws Exception {
        Enumeration e = new Vector().elements();
        
        // RIGHT: m1 is a public method in a public interface.
        Class c1 = Enumeration.class;
        Method m1 = c1.getMethod("hasMoreElements", new Class[] {});
        System.out.println(m1.invoke(e, new Object[] {})); 
                                                // false

        // WRONG: m2 is a public method in a private class.
        Class c2 = e.getClass();        
        Method m2 = c2.getMethod("hasMoreElements", new Class[] {});
        System.out.println(m2.invoke(e, new Object[] {})); 
                                                // IllegalAcessException
    }
}
