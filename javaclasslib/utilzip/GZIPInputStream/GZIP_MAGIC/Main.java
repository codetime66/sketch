import java.io.*;
import java.util.zip.*;

class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Main <filename>");
        } else {
            try {
                InputStream is = new FileInputStream(args[0]);
    
                // Read the 16-bit value.
                int magic = is.read() | (is.read() << 8);

                if (magic == GZIPInputStream.GZIP_MAGIC) {
                    System.out.println("This is a GZIP file");
                } else {
                    System.out.println("This is not a GZIP file.");
                    System.out.println("Its magic number is 0x" + 
                        Integer.toHexString(magic) + " and not 0x" + 
                        Integer.toHexString(GZIPInputStream.GZIP_MAGIC));
                }
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
