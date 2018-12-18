import java.io.*;

class Main {
    public static void main(String[] args) {
        try {
            // Write it out
            FileOutputStream f = new FileOutputStream("ClassE.ser");
            ObjectOutput out = new ObjectOutputStream(f);

            out.writeObject(new ClassE(30, "Hello"));
            out.flush();
            out.close();

            // Read it back
            FileInputStream f2 = new FileInputStream("ClassE.ser");
            ObjectInputStream in = new ObjectInputStream(f2);
            ClassE eobj = (ClassE) in.readObject();
            in.close();
            System.out.println("eobj.field1 " + eobj.field1);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

class ClassE  implements Externalizable {
    int field1;
    String field2;

    public ClassE(int i, String s) {
        field1 = i;
        field2 = s;
    }

    // Required for Externalizable
    public ClassE() {
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeBytes(String.valueOf(field1));
        out.write('\n');
        out.writeBytes(field2);
        out.write('\n');
    }

    public void readExternal(ObjectInput in) throws IOException, 
        ClassNotFoundException {
        field1 = Integer.parseInt(in.readLine());
        field2 = in.readLine();
    }
}
