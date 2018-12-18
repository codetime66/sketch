import java.io.*;
import java.util.zip.*;

class Main {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Main <file>");
            System.exit(1);
        }

        try {
            // Create the input streams.
            CheckedInputStream cis = new CheckedInputStream(
                new BufferedInputStream(
                new FileInputStream(args[0])), new Adler32());

            // Create the output streams.
            CheckedOutputStream cos = new CheckedOutputStream(
                new FileOutputStream(args[0]+".zip"), new Adler32());
            OutputStream os = new BufferedOutputStream(
                new GZIPOutputStream(cos));

            byte[] buf = new byte[1024];
            int len;
    
            // Transfer from the input stream to the output stream.
            while ((len = cis.read(buf)) >= 0) {
                os.write(buf, 0, len);
            }

            // Print the checksum value of the compressed output stream.
            System.out.println("input:  "+cis.getChecksum().getValue());
            System.out.println("output: "+cos.getChecksum().getValue());

            // Clean up.
            cis.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}