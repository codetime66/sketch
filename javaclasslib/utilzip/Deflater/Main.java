import java.io.*;
import java.util.zip.*;

class Main {
    static void deflate(Deflater def, InputStream in) throws IOException {
            byte[] inbuf = new byte[1024];
            byte[] outbuf = new byte[1024];
            int inlen;

            // Read and uncompressed data and give it to the deflater.
            while ((inlen = in.read(inbuf)) >= 0) {
                def.setInput(inbuf, 0, inlen);
                while (!def.needsInput()) {
                    // Read the deflated data and discard it.
                    def.deflate(outbuf);
                }
            }

            // There is no more uncompressed data.
            def.finish();

            // Read the rest of the compressed data.
            while (!def.finished()) {
                def.deflate(outbuf);
            }

            // Print statistics.
            System.out.println("  " + def.getTotalIn() + " -> " + 
                               def.getTotalOut());
            System.out.println("  adler: 0x" + 
                               Integer.toHexString(def.getAdler()));
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Usage: java Main <filename1>...");
            System.exit(1);
        }

        JavaDeflater javadef = new JavaDeflater();
        Deflater def = new Deflater();
        
        for (int i=0; i<args.length; i++) {
            try {
                // Without dictionary.
                System.out.println(args[i] + " (no dictionary)");
                deflate(def, new FileInputStream(args[i]));

                // With dictionary.
                System.out.println(args[i] + " (with dictionary)");
                deflate(javadef, new FileInputStream(args[i]));
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Reset the deflaters for another file.
            javadef.reset();
            def.reset();
        }
    }
}
