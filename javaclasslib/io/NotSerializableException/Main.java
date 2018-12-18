import java.io.*;

class Main {
    public static void main(String[] args) {
        try {
            FileOutputStream f = new FileOutputStream("Class1.ser");
            ObjectOutput out = new ObjectOutputStream(f);

            out.writeObject(new Class1(10, 20));
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Class1 {
    int field1;
    int field2;

    Class1(int one, int two) {
        field1 = one;
        field2 = two;
    }
}
