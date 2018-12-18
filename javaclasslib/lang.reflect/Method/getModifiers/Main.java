import java.lang.reflect.*;
import java.io.*;
import java.util.*;

class Main {
    Main(Class c) {
        Method methods[] = c.getMethods();

        for (int i=0; i<methods.length; i++) {
            int mods = methods[i].getModifiers() & ~Modifier.PUBLIC;
            if (mods != 0) {
                System.out.print(Modifier.toString(mods) + " ");
            }
            System.out.print(methods[i].getReturnType().getName());
            System.out.print(" ");
            System.out.println(methods[i].getName() + "()");
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
