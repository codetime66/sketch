import java.io.*;

class TestStream extends ObjectOutputStream {
    TestStream(OutputStream out) throws IOException, StreamCorruptedException {
        super(out);
        enableReplaceObject(true);
    }

    protected Object replaceObject(Object obj) {
        if (obj instanceof TargetClass) {
            return new NewClass(((TargetClass)obj).field1);
        }
        return obj; // otherwise just return original
    }
}

class TargetClass implements Serializable {
    public int field1;

    TargetClass(int i) {
        field1 = i;
    }

    public String toString() {
        return (field1+"");
    }
}

class NewClass extends TargetClass {
    public int field2;
    NewClass(int i) {
        super(i);
        field2 = field1*2;
    }

    public String toString() {
        return ("field1: " + field1 + " field2: " + field2);
    }
}

class Main {
    public static void main(String[] args) {
        try {
            // Write class out
            FileOutputStream f = new FileOutputStream("Target.ser");
            ObjectOutputStream out = new TestStream(f);

            TargetClass a = new TargetClass(10);
            out.writeObject(a);
            out.flush();
            out.close();

            // Read it back
            FileInputStream f2 = new FileInputStream("Target.ser");
            ObjectInput in = new ObjectInputStream(f2);
            Object aa = in.readObject();
            in.close();

            System.out.println(aa);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
