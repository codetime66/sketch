import java.lang.reflect.*;
import java.io.*;

class Main {
    Main(Class c) {
        Constructor con = null;
        Object obj = null;
        
        // Get the constructor that takes no arguments
        try {
            con = c.getConstructor(new Class[] {});
        } catch (NoSuchMethodException e) {
            System.out.println(
                "\nThere is no constructor that takes no arguments.");
            return;
        }
        
        // Another way of getting the constructor with no arguments
        boolean found = false;
        Constructor[] cons = c.getConstructors();
        for (int i=0; i<cons.length && !found; i++) {
            if (cons[i].getParameterTypes().length == 0) {
                con = cons[i];
                found = true;
            }
        }
        if (!found) {
            System.out.println(
                "\nThere is no constructor that takes no arguments.");
            return;
        }
        
        try {
            // Now create object.
            obj = con.newInstance(new Object[] {});
        } catch (InstantiationException e) {
            e.printStackTrace();
            return;
        } catch (InvocationTargetException e) {
            e.getTargetException().printStackTrace();
            return;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return;
        }
        
        try {
            // Print message
            System.out.print(
                "Serializing " + con.getDeclaringClass().getName() 
                + " to " + con.getName() + ".ser ... ");
            
            ObjectOutputStream os = new ObjectOutputStream(
                new FileOutputStream(con.getName() + ".ser"));
            os.writeObject(obj);
            os.close();
            
            System.out.println("done");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
