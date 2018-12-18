import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) {

        try {
            // Produce serialization file
            FileOutputStream f = new FileOutputStream("test.ser");
            ObjectOutput out = new ObjectOutputStream(f);

            out.writeInt(100);
            out.writeByte(8);
            out.writeObject(new Date());
            out.writeDouble(10.3f);
            out.writeShort(99);
            out.writeObject("Java");
            out.writeObject(new Vector());
            out.close();

            // Read in serialization file
            FileInputStream f2 = new FileInputStream("test.ser");
            ObjectInputStream in = new ObjectInputStream(f2);

            while (true) {
                try {
                    System.out.println(in.readObject());
                                // 1st time: Sat Dec 13 20:43:55 PST 1997
                                // 2nd time: Java
                                // 3rd time: []
                } catch (OptionalDataException ode) {
                    if (ode.eof) {
                        in.close();
                        break;
                    }
                    // Skip primitive data
                    in.skipBytes(ode.length);
                } catch (EOFException e) {
                    in.close();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
