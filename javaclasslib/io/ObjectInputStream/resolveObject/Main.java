import java.io.*;

class TestStream extends ObjectInputStream {
    TestStream(InputStream in) throws IOException, StreamCorruptedException {
        super(in);
        enableResolveObject(true);
    }

    protected Object resolveObject(Object obj) {
        if (obj instanceof AncientClass) {
            return new UpToDateClass(((AncientClass)obj).field1);
        }
        return obj; // otherwise just return original
    }
}

class AncientClass implements Serializable {
    int field1;

    AncientClass(int i) {
        field1 = i;
    }
    int doub() {
        System.out.println("Using addition");
        return (field1 += field1);
    }
}

class UpToDateClass extends AncientClass {
    UpToDateClass(int f) {
        super(f);
    }
    int doub() {
        System.out.println("Using shift");
        return (field1 <<= 1);
    }
}

class Main {
    public static void main(String[] args) {
        try {
            // Write class out
            FileOutputStream f = new FileOutputStream("Ancient.ser");
            ObjectOutput out = new ObjectOutputStream(f);

            AncientClass a = new AncientClass(10);
            System.out.println("first answer: " + a.doub());
            out.writeObject(a);
            out.flush();
            out.close();

            // Read it back
            FileInputStream f2 = new FileInputStream("Ancient.ser");
            TestStream in = new TestStream(f2);
            AncientClass aa = (AncientClass) in.readObject();
            in.close();

            System.out.println("after answer: " + aa.doub());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
