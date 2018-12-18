import java.io.*;
import java.util.zip.Adler32;

class Main {
     public static void main(String[] args) {
         ByteArrayOutputStream buf = new ByteArrayOutputStream();
         Adler32 checksum = new Adler32();

         for (int i = 0; i < 26; i++) {
             buf.write('a'+i);
         }

         // Compute checksum of 'a' - 'z'
         checksum.update(buf.toByteArray(), 0, buf.size());
         System.out.println("first checksum: " + checksum.getValue());

         // Reset buffer and checksum
         checksum.reset();
         buf.reset();

         for (int i = 0; i < 10; i++) {
             buf.write('0'+i);
         }

         checksum.update(buf.toByteArray(), 0, buf.size());
         System.out.println("second checksum: " + checksum.getValue());
    }
}
