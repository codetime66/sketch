import java.io.*;
import java.util.zip.*;

class Main {
    static long getChecksumValue(Checksum checksum, String fname) {
        try {
            BufferedInputStream is = new BufferedInputStream(
                new FileInputStream(fname));
            byte[] bytes = new byte[1024];
            int len = 0;
            
            // Read the file and compute the checksum.
            while ((len = is.read(bytes)) >= 0) {
                checksum.update(bytes, 0, len);
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return checksum.getValue();
    }
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Main <file>");
            System.exit(1);
        }

        // Measure the performance of CRC32.
        long time = System.currentTimeMillis();
        long cv = getChecksumValue(new CRC32(), args[0]);
        time = System.currentTimeMillis() - time;
        System.out.println("crc32");
        System.out.println("    checksum: " + cv);
        System.out.println("    time    : " + time + "ms");

        // Measure the performance of Adler32.
        time = System.currentTimeMillis();
        cv = getChecksumValue(new Adler32(), args[0]);
        time = System.currentTimeMillis() - time;
        System.out.println("adler32");
        System.out.println("    checksum: " + cv);
        System.out.println("    time    : " + time + "ms");
    }
}
