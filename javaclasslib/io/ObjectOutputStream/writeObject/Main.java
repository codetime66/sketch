import java.io.*;
import java.util.Date;

class Main {
    public static void main(String[] args) {
        try {
            // Write class out
            FileOutputStream f = new FileOutputStream("Test.ser");
            ObjectOutput out = new ObjectOutputStream(f);

            out.writeObject("Java Class Libraries");
            out.writeObject(new Date());
            out.writeObject(null);
            int[] ints = {1, 2, 3, 4, 5};
            out.writeObject(ints);
            out.flush();
            out.close();

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
