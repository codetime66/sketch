import java.io.*;
import java.util.Date;

class TestInputStream extends ObjectInputStream {
    TestInputStream(InputStream in) 
        throws IOException, StreamCorruptedException {
        super(in);
    }

    protected Class resolveClass(ObjectStreamClass v)
        throws IOException, ClassNotFoundException {
            String classdir = readUTF();
            System.out.println("Loading class from directory " + classdir);
            ClassLoader cl = new FileClassLoader(classdir);
            return cl.loadClass(v.getName());
        }
}

class TestOutputStream extends ObjectOutputStream {
    String classdir;
    TestOutputStream(OutputStream out, String cd) throws IOException {
        super(out);
        classdir = cd;
    }

    protected void annotateClass(Class cl) throws IOException {
        System.out.println("Recording directory to load class " + classdir);
        writeUTF(classdir);
    }
}

class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Main <directory>");
            System.exit(-1);
        }
        try {
            // Write class out
            FileOutputStream f = new FileOutputStream("Class1.ser");
            ObjectOutput out = new TestOutputStream(f, args[0]);

            out.writeObject(new Class1());
            out.flush();
            out.close();

            // Read it back
            FileInputStream f2 = new FileInputStream("Class1.ser");

// Using a plain ObjectInputStream would throw StreamCorruptedException
//            ObjectInput in = new ObjectInputStream(f2); 

            ObjectInput in = new TestInputStream(f2);
            Class1 c1 = (Class1) in.readObject();
            in.close();

            System.out.println("field1: " + c1.field1);
            System.out.println("field2: " + c1.field2);
            System.out.println("field3: " + c1.field3);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

class Class1 implements Serializable {
    int field1;
    String field2;
    transient int field3;

    public Class1() {
        field1 = 10;
        field2 = "a string";
        field3 = -1;
    }
}
