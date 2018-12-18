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
                int primData = in.available();
                if (primData > 0) {
                    System.out.println("skipped " + in.skipBytes(primData));
                }
                System.out.println(in.readObject());
            }
        } catch (EOFException e) {
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
