import java.io.*;

class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println(
                "Usage: java Main <temp output file>");
            System.exit(-1);
        }
        try {
            // write it out
            FileOutputStream fout = new FileOutputStream(args[0]);
            DataOutputStream out = new DataOutputStream(fout);
            String str = "This is a test\n";
            out.writeDouble(10.02); // introduce bogus value
            out.writeUTF(str);
            out.close();

            // read it back
            FileInputStream fin = new FileInputStream(args[0]);
            DataInputStream in = new DataInputStream(fin);

            System.out.println(in.readUTF());
        } catch (UTFDataFormatException e) {
            System.err.println("UTF error");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
