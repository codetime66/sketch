import java.io.*;
import java.util.Date;

class Main {
    public static void main(String[] args) {
        try {
            // Write class out
            FileOutputStream f = new FileOutputStream("Class1.ser");
            ObjectOutput out = new ObjectOutputStream(f);

            Class1 c1 = new Class1();
            c1.date = new Date();
            out.writeObject(c1);
            out.flush();
            out.close();

            // Read it back
            FileInputStream f2 = new FileInputStream("Class1.ser");
            ObjectInputStream in = new ObjectInputStream(f2);
            Class1 cc1 = (Class1) in.readObject();
            in.close();

            System.out.println("cc1.date " + cc1.date);
            System.out.println("cc1.restored " + cc1.restored);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

class Class1 implements Serializable {
    transient boolean restored;
    public Date date;
    transient String str;

    private void readObject(ObjectInputStream in) 
    throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        restored = true;
    }

    // Does just default serialization
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }
}
