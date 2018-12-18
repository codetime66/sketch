import java.lang.reflect.*;
import java.io.*;
import java.util.*;

class Main {
    Main(Class c) {
        Field fields[] = c.getFields();

        for (int i=0; i<fields.length; i++) {
            int mods = fields[i].getModifiers();
            System.out.print(Modifier.toString(mods & ~Modifier.PUBLIC));
            System.out.print(" ");
            System.out.print(fields[i].getType().getName());
            System.out.print(" ");
            System.out.println(fields[i].getName());
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
