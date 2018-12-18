import java.io.*;

class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println(
                "Usage: java Main <output file>");
            System.exit(-1);
        }
        try {
            RandomAccessFile raf = new RandomAccessFile(args[0], "rw");

            char a = 'a';
            byte b = 2;
            String c = "abc";
            short d = 4;
            byte[] b2 = {'a', 'b', 'c'};

            // write some stuff out
            long file_start = raf.getFilePointer();
            raf.write(b);
            raf.write(b2, 0, b2.length);
            raf.writeBoolean(true);
            raf.writeChar(a);
            raf.writeBytes(c);
            raf.writeChars(c);
            raf.writeDouble(123.456);
            raf.writeFloat(123.456f);
            raf.writeInt(678);
            raf.writeLong(678l);
            raf.writeShort(d);
            raf.writeUTF(c);
            raf.writeUTF("abc\n");
            raf.write(b);
            raf.writeShort(d);

            System.out.println("Length of file: " + raf.length());

            // read the stuff back
            raf.seek(file_start);

            b2 = new byte[1];
            b = raf.readByte();
            System.out.println("Byte: " + b);
            raf.read(b2);
            System.out.println("Byte[0]: " + (char)b2[0]);
            raf.read(b2, 0, b2.length);
            System.out.println("Byte[0]: " + (char)b2[0]);
            int ub = raf.readUnsignedByte();
            System.out.println("Unsigned Byte: " + b);
            System.out.println("Boolean: " + raf.readBoolean());
            a = raf.readChar();
            System.out.println("Char: " + a);
            
            byte[] b3 = new byte[3];
            raf.readFully(b3);
            System.out.println("readFully: " + (char)b3[0] + (char)b3[1] +
                               (char)b3[2]);
            raf.skipBytes(6); // skip string 'abc'
            double d1 = raf.readDouble();
            float f1 = raf.readFloat();
            int i = raf.readInt();
            long l = raf.readLong();
            short s = raf.readShort();
            String str = raf.readUTF();
            ub = raf.readUnsignedByte();
            int us = raf.readUnsignedShort();
            System.out.println("UTF String" + str);

            raf.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
