import java.lang.reflect.*;
import java.io.*;
import java.util.*;

class Main {
    // Returns a non-null vector containing any methods that throw
    // at least one of the exceptions in excs.
    static Vector isThrows(Class c, Class[] excs) {
        Vector v = new Vector();
        Method[] methods = c.getDeclaredMethods();

        // Scan each method.
        for (int i=0; i<methods.length; i++) {
            Class[] es = methods[i].getExceptionTypes();
            
            // Scan each of the method's exceptions.
        search:
            for (int j=0; j<es.length; j++) {
                // Check against each of the user's specified list of exceptions.
                for (int k=0; k<excs.length; k++) {
                    if (es[j] == excs[k]) {
                        // Found a match.
                        v.addElement(methods[i]);
                        break search;
                    }
                }
            }
        }
        return v;
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println(
                "Usage: java Main <file of class names> <exception>...");
            System.exit(1);
        }

        try {
            Class[] exceptions = new Class[args.length-1];
            BufferedReader in = new BufferedReader(new FileReader(args[0]));

            // Create class objects for each of the specified exceptions.
            for (int i=1; i<args.length; i++) {
                exceptions[i-1] = Class.forName(args[i]);
            }

            // Read the input file and retrieve their Class objects.
            String classname = null;
            while ((classname = in.readLine()) != null) {
                Class c = Class.forName(classname);
                Vector v = isThrows(c, exceptions);

                for (int i=0; i<v.size(); i++) {
                    Method m = (Method)v.elementAt(i);
                    System.out.println(m.getDeclaringClass().getName()
                        + "." + m.getName() + "()");
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
