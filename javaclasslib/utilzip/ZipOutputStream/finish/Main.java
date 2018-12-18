import java.util.zip.*;

class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Main <string>");
        } else {
            try {
                String str = args[0];
            // Write uncompressed zip file.
                ZipOutputStream os = new ZipOutputStream(System.out);
                os.setMethod(ZipOutputStream.STORED);
                os.setComment("uncompressed");

                // Create entry.  
                ZipEntry ze = new ZipEntry(str);
                ze.setSize(str.length());

                // Determine the checksum of the string.
                CRC32 crc = new CRC32();
                crc.update(str.getBytes(), 0, str.length());
                ze.setCrc(crc.getValue());

                // Write entry and data.
                os.putNextEntry(ze);
                os.write(str.getBytes(), 0, str.length());

                // Finish the zip output stream without closing it 
                // so we can write a new zip file.
                os.finish();

            // Write compressed zip file.
                // Create a new zip output stream on the same output stream.
                os = new ZipOutputStream(System.out);
                os.setMethod(ZipOutputStream.DEFLATED);
                os.setComment("compressed");

                // Write entry and data.
                os.putNextEntry(new ZipEntry(str));
                os.write(str.getBytes(), 0, str.length());

                // Now it's safe to close.
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
