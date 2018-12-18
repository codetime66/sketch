import java.io.*;
import java.util.zip.*;

class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Main <file>");
            System.exit(1);
        }

        try {
            CheckedInputStream is = new CheckedInputStream(
                new BufferedInputStream(
                new FileInputStream(args[0])), new XorChecksum());
            byte[] bytes = new byte[1024];
            int len = 0;

            // Read the file and compute the checksum.
            while ((len = is.read(bytes)) >= 0) {
            }
            is.close();
            System.out.println(is.getChecksum().getValue());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class XorChecksum implements Checksum {
    long chksum = 0;

    public long getValue() {
        return chksum;
    }
    public void update(int b) {
        chksum ^= (b & 0xFF);
    }
    public void update(byte[] b, int off, int len) {
        for (int i=0; i<len; i++) {
            chksum ^= (b[off+i] & 0xFF);
        }
    }
    public void reset() {
        chksum = 0;
    }
}
