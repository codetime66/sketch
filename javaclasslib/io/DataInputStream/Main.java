import java.io.*;

class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println(
                "Usage: java Main <output from DataOutput example>");
            System.exit(-1);
        }
        FileInputStream file_in;
        DataInputStream data_in;

        try {
            file_in = new FileInputStream(args[0]);
            data_in = new DataInputStream(file_in);

            System.out.println("Available: " + data_in.available());

            byte b;
            byte[] b2 = new byte[1];
            b = data_in.readByte();
            System.out.println("Byte: " + b);
            data_in.read(b2);
            System.out.println("Byte[0]: " + (char)b2[0]);
            data_in.read(b2, 0, b2.length);
            System.out.println("Byte[0]: " + (char)b2[0]);
            int ub = data_in.readUnsignedByte();
            System.out.println("Unsigned Byte: " + b);
            System.out.println("Boolean: " + data_in.readBoolean());
            char a = data_in.readChar();
            System.out.println("Char: " + a);
            
            byte[] b3 = new byte[3];
            data_in.readFully(b3);
            System.out.println("readFully: " + (char)b3[0] + (char)b3[1] +
                               (char)b3[2]);
            data_in.skipBytes(6); // skip string 'abc'
            double d1 = data_in.readDouble();
            float f1 = data_in.readFloat();
            int i = data_in.readInt();
            long l = data_in.readLong();
            short s = data_in.readShort();
            String str = data_in.readUTF();
            ub = data_in.readUnsignedByte();
            int us = data_in.readUnsignedShort();
            System.out.println("UTF String" + str);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
