import java.io.*;

class Main {
    public static void main(String[] args) {
        try {
            // Write object out
            FileOutputStream f = new FileOutputStream("Class1.ser");
            ObjectOutput out = new ObjectOutputStream(f);

            Class1 c1 = new Class1(10);
            out.writeObject(c1);
            out.flush();
            out.close();

            // Read it back
            FileInputStream f2 = new FileInputStream("Class1.ser");
            ObjectInputStream in = new ObjectInputStream(f2);
            Class1 cc1 = (Class1) in.readObject();
            in.close();

            System.out.println("c1.field1 " + cc1.field1);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

class Class1 extends Class2 implements Serializable {
    int field1;
    transient int field2;

    public Class1(int ignore) {
        super(ignore);
        field1 = 10;
        field2 = -1;
    }
}

class Class2 {
    int field1;
    int field2;

    public Class2(int ignore) {}
}
