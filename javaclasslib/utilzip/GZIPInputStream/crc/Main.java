import java.io.*;
import java.util.zip.*;

class Main {
    public static void main(String[] args) {
        try {
            MyGZIPInputStream is = new MyGZIPInputStream(System.in);
            byte[] buf = new byte[1024];
            int len;

            while (!is.getEos()) {
                len = is.read(buf);
                if (len > 0) {
                    System.out.write(buf, 0, len);
                }
            }
            is.close();
            System.err.println("\nThe checksum is: " + 
                               is.getChecksum().getValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class MyGZIPInputStream extends GZIPInputStream {
    MyGZIPInputStream(InputStream in) throws IOException {
        super(in);
    }
    boolean getEos() {
        return eos;
    }
    Checksum getChecksum() {
        return crc;
    }
}
