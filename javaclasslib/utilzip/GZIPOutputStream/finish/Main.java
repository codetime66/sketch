import java.io.*;
import java.util.zip.*;

class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java Main <filename>...");
            System.exit(1);
        } 

        byte[] buf = new byte[1024];
        int len;
        try {
            GZIPOutputStream os = null;
            for (int i=0; i<args.length; i++) {
                // Open the input file.
                FileInputStream is = new FileInputStream(args[i]);

                // Create a new GZIP output stream on the same output stream.
                os = new GZIPOutputStream(System.out);

                // Transfer the data from the input file to the output file.
                while ((len = is.read(buf)) >= 0) {
                    os.write(buf, 0, len);
                }

                // Close the input file.
                is.close();

                // Write out GZIP trailer information.
                os.finish();
            }
            // Now it's safe to close.
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
