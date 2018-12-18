import java.lang.reflect.*;
import java.util.*;

class Main {
    Main(Class c) {
        Vector superMethods = new Vector();
        Method[] methods = c.getDeclaredMethods();
        
        // Retrieve the declared methods of c's superclasses.
        c = c.getSuperclass();
        while (c != null) {
            Method[] ms = c.getDeclaredMethods();
            for (int i=0; i<ms.length; i++) {
                superMethods.addElement(ms[i]);
            }
            c = c.getSuperclass();
        }

        // Compare each method in c with the methods in all of its superclasses.
        for (int i=0; i<methods.length; i++) {
            for (int j=0; j<superMethods.size(); j++) {
                Method m2 = (Method)superMethods.elementAt(j);

                if (sameNameAndSignature(methods[i], m2)) {
                    System.out.println(methods[i]);
                    System.out.println("   overrides " 
                        + m2.getDeclaringClass().getName());
                    break;
                }
            }
        }
    }

    boolean sameNameAndSignature(Method m1, Method m2) {
        // Is either private?
        if (Modifier.isPrivate(m1.getModifiers()) 
            || Modifier.isPrivate(m2.getModifiers())) {
            return false;
        }

        // Are the names the same?
        if (!m1.getName().equals(m2.getName())) {
            return false;
        }

        // Get the parameter type lists.
        Class[] p1 = m1.getParameterTypes();
        Class[] p2 = m2.getParameterTypes();

        // Are the parameter list sizes the same?
        if (p1.length != p2.length) {
            return false;
        }

        // Are the types the same?
        for (int i=0; i<p1.length; i++) {
            if (p1[i] != p2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Main <classname>");
        } else {
            try {
                new Main(Class.forName(args[0]));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
