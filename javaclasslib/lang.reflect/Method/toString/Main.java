import java.lang.reflect.*;
import java.io.*;
import java.util.*;

class Main {
    Main(Class c) {
        Method methods[] = c.getDeclaredMethods();

        for (int i=0; i<methods.length; i++) {
            System.out.println(methods[i].toString());
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
