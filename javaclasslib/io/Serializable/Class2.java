import java.io.*;

public class Class2 implements Serializable {
    int field1;
    String field2;
    transient int field3;

    public Class2() {
	field1 = 10;
	field2 = "a string";
	field3 = -1;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
	out.writeInt(field1);
	out.writeUTF(field2);
    }

    private void readObject(ObjectInputStream in) 
	throws IOException, ClassNotFoundException {
	field1 = in.readInt();
	field2 = in.readUTF();
    }
}

