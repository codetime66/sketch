import java.io.*;

class Main {
    public static void main(String[] args) {
        try {
            // Write object out
            FileOutputStream f = new FileOutputStream("Class1.ser");
            ObjectOutputStream out = new ObjectOutputStream(f);

            Class1 c1 = new Class1(10);
            out.defaultWriteObject();
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Class1 implements Serializable {
    int field1;
    transient int field2;

    public Class1(int ignore) {
        field1 = 10;
        field2 = -1;
    }
}
