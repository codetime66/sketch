import java.io.*;
import java.util.zip.*;

class Main {
    public static void main(String[] args) {
        try {
            MyGZIPOutputStream os = new MyGZIPOutputStream(System.out);
            byte[] buf = new byte[1024];
            int len;

            while ((len = System.in.read(buf)) > 0) {
                os.write(buf, 0, len);
            }
            os.close();
            System.err.println("\nThe checksum is: " + 
                               os.getChecksum().getValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class MyGZIPOutputStream extends GZIPOutputStream {
    MyGZIPOutputStream(OutputStream out) throws IOException {
        super(out);
    }
    Checksum getChecksum() {
        return crc;
    }
}
