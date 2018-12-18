import java.io.*;
import java.util.Date;

class Main {
    public static void main(String[] args) {
        try {
            // Write class out
            FileOutputStream f = new FileOutputStream("Class1.ser");
            ObjectOutput out = new ObjectOutputStream(f);

            Class1 c1 = new Class1(11, 22);
            out.writeObject(c1);
            out.writeObject(new Date());
            out.flush();
            out.close();

            // Read it back
            FileInputStream f2 = new FileInputStream("Class1.ser");
            ObjectInputStream in = new ObjectInputStream(f2);
            Class1 cc1 = (Class1) in.readObject();
            System.out.println("date: " + in.readObject());
            in.close();

            System.out.println(cc1.a + " " + cc1.b);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

class Class1 implements Serializable, ObjectInputValidation {
    int a, b;

    public Class1(int aa, int bb) {
        a = aa;
        b = bb;
    }
    public void validateObject() throws InvalidObjectException {
        System.out.println("Validating object");
        if (a < 0 || b < 0) {
            throw new InvalidObjectException("Fields cannot be negative");
        }
    }
    private void readObject(ObjectInputStream in) 
        throws IOException, ClassNotFoundException {
            in.registerValidation(this, 1);
            in.defaultReadObject();
    }
}
