import java.io.*;
import java.util.Date;

class Main {
    public static void main(String[] args) {
        try {
            // Write 2 instances of Date
            FileOutputStream f = new FileOutputStream("Test1.ser");
            ObjectOutputStream out = new ObjectOutputStream(f);

            Date d = new Date();
            out.writeObject(d);
            out.writeObject(d);
            out.flush();
            out.close();

            // Write 2 instances of Date, separated by reset()
            f = new FileOutputStream("Test2.ser");
            out = new ObjectOutputStream(f);

            out.writeObject(d);
            out.reset();
            out.writeObject(d);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
