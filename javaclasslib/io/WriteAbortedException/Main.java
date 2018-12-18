import java.io.*;
import java.util.Date;

class Main {
    public static void main(String[] args) {
        try {
            // Write class out
            FileOutputStream f = new FileOutputStream("Test.ser");
            ObjectOutputStream out = new ObjectOutputStream(f);
            out.writeObject("Java Class Libraries");
            out.writeObject(new Date());
            out.flush();
            out.writeObject(new ClassX()); // not serializable
            out.close();
        } catch (IOException e) {
            System.err.println(e);
        }

        try {
            // Read it back
            FileInputStream f2 = new FileInputStream("Test.ser");
            ObjectInputStream in = new ObjectInputStream(f2);
            while (true) {
                try {
                    Object obj = in.readObject();
                    System.out.println(obj);
                } catch (EOFException e) {
                    System.out.println("done");
                    break;
                }
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

class ClassX {
    int fieldx;
}
