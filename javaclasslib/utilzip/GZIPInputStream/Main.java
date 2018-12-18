import java.io.*;
import java.util.zip.*;

class Main {
    public static void main(String[] args) {
        try {
            GZIPInputStream is = new GZIPInputStream(System.in);
            byte[] buf = new byte[1024];
            int len;

            while ((len = is.read(buf)) > 0) {
                System.out.write(buf, 0, len);
            }
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
