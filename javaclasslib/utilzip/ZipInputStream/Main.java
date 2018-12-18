import java.io.*;
import java.util.zip.*;

class Main {
    public static void main(String[] args) {
        try {
            ZipInputStream is = new ZipInputStream(System.in);
            ZipEntry ze;
            byte[] buf = new byte[128];
            int len;

            while ((ze = is.getNextEntry()) != null) {
                System.out.println("----------- " + ze);

                // Determine the number of bytes to skip and skip them.
                int skip = (int)ze.getSize() - 128;
                while (skip > 0) {
                    skip -= is.skip(Math.min(skip, 512));
                }

                // Read the remaining bytes and if it's printable, print them.
                out: while ((len = is.read(buf)) >= 0) {
                    for (int i=0; i<len; i++) {
                        if ((buf[i]&0xFF) >= 0x80) {
                            System.out.println("**** UNPRINTABLE ****");
                            
                            // This isn't really necessary since getNextEntry()
                            // automatically calls it.
                            is.closeEntry();

                            // Get the next zip entry.
                            break out;
                        }
                    }
                    System.out.write(buf, 0, len);
                }
            }
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
