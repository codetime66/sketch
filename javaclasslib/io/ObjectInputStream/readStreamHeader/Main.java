import java.io.*;
import java.util.Date;

class TestInputStream extends ObjectInputStream {
    TestInputStream(InputStream in) 
        throws IOException, StreamCorruptedException {
        super(in);
    }

    protected void readStreamHeader()
        throws IOException, StreamCorruptedException {
        super.readStreamHeader();
        short my_magic = readShort();

        if (my_magic != TestOutputStream.TEST_MAGIC) {
            throw new StreamCorruptedException(
               "Stream not generated from TestOutputStream");
        }
    }
}

class TestOutputStream extends ObjectOutputStream {
    static final int TEST_MAGIC = 100;

    TestOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    protected void writeStreamHeader() throws IOException {
        super.writeStreamHeader();
        writeShort(TEST_MAGIC);
    }
}

class Main {
    public static void main(String[] args) {
        try {
            // Write class out
            FileOutputStream f = new FileOutputStream("Date.ser");
            ObjectOutput out = new TestOutputStream(f);

            out.writeObject(new Date());
            out.flush();
            out.close();

            // Read it back
            FileInputStream f2 = new FileInputStream("Date.ser");

// Using a plain ObjectInputStream would throw StreamCorruptedException
//            ObjectInput in = new ObjectInputStream(f2); 

            ObjectInput in = new TestInputStream(f2);
            Date d = (Date) in.readObject();
            in.close();

            System.out.println("answer: " + d);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
