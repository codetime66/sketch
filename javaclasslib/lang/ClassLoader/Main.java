import java.net.*;
import java.io.*;

class Main {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println(
                "usage: java Main <dir> <classname> <resource>");
            System.exit(-1);
        }
        
        try {
            ClassLoader cl = new FileClassLoader(args[0]);
            Class c = cl.loadClass(args[1]);

            System.out.println("Class: " + c);
            System.out.println("Resource: " + cl.getResource(args[2]));
            System.out.println("Resource Stream: " + cl.getResourceAsStream(args[2]));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
