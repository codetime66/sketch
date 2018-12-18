import java.io.*;
import java.util.zip.*;

class Main {

    static void zipit(InputStream is, int strategy) 
        throws ZipException, IOException {
        // Create the entry and output streams.
        Deflater def = new Deflater();
        DeflaterOutputStream os = new DeflaterOutputStream(
            new NullOutputStream(), def);

        def.setStrategy(strategy);

        int len = 0;
        byte[] buf = new byte[1024];
        long time = System.currentTimeMillis();

        // Set compression level and write entry.

        // Write data.
        while ((len = is.read(buf)) >= 0) {
            os.write(buf, 0, len);
        }
        os.close();

        System.out.println("strategy: " + strategy);
        System.out.println("    " + def.getTotalIn() + " -> " + 
                           def.getTotalOut());
        System.out.println("    time: " + 
                           (System.currentTimeMillis()-time) + "ms");
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Main <filename>");
        } else {
            try {
                zipit(new FileInputStream(args[0]), Deflater.DEFAULT_STRATEGY);
                zipit(new FileInputStream(args[0]), Deflater.FILTERED);
                zipit(new FileInputStream(args[0]), Deflater.HUFFMAN_ONLY);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class NullOutputStream extends OutputStream {
    public void write(int b) {}
    public void write(byte[] buf, int off, int len) {}
}
