import java.io.*;

class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Main <output file>");
            System.exit(-1);
        }
        FileOutputStream file_out;
        DataOutputStream data_out;

        try {
            file_out = new FileOutputStream(args[0]);
            data_out = new DataOutputStream(file_out);

            char a = 'a';
            byte b = 2;
            String c = "abc";
            short d = 4;
            byte[] b2 = {'a', 'b', 'c'};

            data_out.write(b);
            data_out.write(b2, 0, b2.length);
            data_out.writeBoolean(true);
            data_out.writeChar(a);
            data_out.writeBytes(c);
            data_out.writeChars(c);
            data_out.writeDouble(123.456);
            data_out.writeFloat(123.456f);
            data_out.writeInt(678);
            data_out.writeLong(678l);
            data_out.writeShort(d);
            data_out.writeUTF(c);
            data_out.writeUTF("abc\n");
            data_out.write(b);
            data_out.writeShort(d);
            data_out.flush();
            System.out.println("Size of file written: " + data_out.size());
            data_out.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
