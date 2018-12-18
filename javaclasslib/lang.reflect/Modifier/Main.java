import java.lang.reflect.*;
import java.io.*;
import java.util.*;

class Main {
    // Converts a modifier constant to its string representation.
    public static String modifier2string(int mod) {
        if (Modifier.isPublic(mod))         return "public";
        if (Modifier.isPrivate(mod))        return "private";
        if (Modifier.isProtected(mod))      return "protected";
        if (Modifier.isAbstract(mod))       return "abstract";
        if (Modifier.isStatic(mod))         return "static";
        if (Modifier.isFinal(mod))          return "final";
        if (Modifier.isTransient(mod))      return "transient";
        if (Modifier.isVolatile(mod))       return "volatile";
        if (Modifier.isNative(mod))         return "native";
        if (Modifier.isSynchronized(mod))   return "synchronized";
        if (Modifier.isInterface(mod))      return "interface";
        return "";
    }

    // Converts the string representation of a modifier to a modifier constant.
    public static int string2modifier(String s) {
        if (s.equals("public"))         return Modifier.PUBLIC;
        if (s.equals("private"))        return Modifier.PRIVATE;
        if (s.equals("protected"))      return Modifier.PROTECTED;
        if (s.equals("abstract"))       return Modifier.ABSTRACT;
        if (s.equals("static"))         return Modifier.STATIC;
        if (s.equals("final"))          return Modifier.FINAL;
        if (s.equals("transient"))      return Modifier.TRANSIENT;
        if (s.equals("volatile"))       return Modifier.VOLATILE;
        if (s.equals("native"))         return Modifier.NATIVE;
        if (s.equals("synchronized"))   return Modifier.SYNCHRONIZED;
        if (s.equals("interface"))      return Modifier.INTERFACE;
        return 0;
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println(
                "Usage: java Main <file of class names> <modifiers>...");
            System.exit(1);
        }

        // Get modifier set.
        int mod = 0;
        for (int i=1; i<args.length; i++) {
            int m = string2modifier(args[i]);
            mod |= m;
            // Could have simply used args[i]; 
            // this is done for demonstration purposes.
            System.out.println("  " + modifier2string(m));
        }
        System.out.println();

        try {
            // Create the input stream.
            BufferedReader is = 
                new BufferedReader(
                new FileReader(args[0]));

            // Read the input file and retrieve their Class objects.
            String classname = null;
            while ((classname = is.readLine()) != null) {
                // Get the class object and then all its methods.
                Class c = Class.forName(classname);
                Method[] methods = c.getDeclaredMethods();

                // Scan each method looking for a modifier set match.
                for (int i=0; i<methods.length; i++) {
                    Method m = (Method)methods[i];

                    if ((m.getModifiers() & mod) == mod) {
                        // Found a match so print the method signature.
                        System.out.println(m);
                    }
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
