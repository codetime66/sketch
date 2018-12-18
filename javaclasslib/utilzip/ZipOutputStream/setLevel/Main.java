import java.io.*;
import java.util.zip.*;

class Main {
    // Compresses the input stream to the output stream.
    static void zipit(InputStream is, OutputStream out, int level) 
                      throws ZipException, IOException {
        // Create the entry and output streams.
        ZipOutputStream os = new ZipOutputStream(out);
        ZipEntry ze = new ZipEntry("test");

        int len = 0;
        int readCount = 0;
        byte[] buf = new byte[1024];
        long time = System.currentTimeMillis();

        // Set compression level and write entry.
        os.setLevel(level);
        os.putNextEntry(ze);

        // Write data.
        while ((len = is.read(buf)) >= 0) {
            os.write(buf, 0, len);
            readCount += len;
        }
        os.close();

        System.out.println("    time: " + 
            (System.currentTimeMillis()-time) + "ms");
        System.out.println("input size: " + readCount + 
            "    level: " + level);
        System.out.println("    compressed size: " + ze.getCompressedSize());
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Main <filename>");
        } else {
            try {
                OutputStream os = new NullOutputStream();

                zipit(new FileInputStream(args[0]), os, 
                    Deflater.DEFAULT_COMPRESSION);
                zipit(new FileInputStream(args[0]), os, 
                    Deflater.NO_COMPRESSION);
                for (int i=Deflater.BEST_SPEED; 
                    i<=Deflater.BEST_COMPRESSION; i++) {
                    zipit(new FileInputStream(args[0]), os, i);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class NullOutputStream extends OutputStream {
    public void write(int b) {
    }
    public void write(byte[] buf, int off, int len) {
    }
}
