import java.io.*;

class ScrambleInputStream extends FilterInputStream {
    private byte[] p = new byte[4];
    int counter = 0;
    public ScrambleInputStream (String passwd, InputStream in) {
        super(in);
        // Compute hash code of password and store into 4 bytes.
        int pw = passwd.hashCode();
        p[0] = (byte) ((pw >>> 24) & 0xFF);
        p[1] = (byte) ((pw >>> 16) & 0xFF);
        p[2] = (byte) ((pw >>> 8) & 0xFF);
        p[3] = (byte) (pw & 0xFF);
    }

    public int read() throws IOException {
        int b = in.read();
        if (b < 0)
            return b;  // end-of-file

        b = ((byte)b ^ p[counter++]) &0xff;
        if (counter == 4)
            counter = 0;
        return b;
    }
}

class Main {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Usage: java Main <passwd> <input> <output>");
            System.exit(-1);
        }
        try {
            FileInputStream in = new FileInputStream(args[1]);
            FileOutputStream out = new FileOutputStream(args[2]);
            InputStream s1 = new ScrambleInputStream(args[0], in);
            for(int c = s1.read(); c > -1; c = s1.read()) {
                out.write(c);
            }
            s1.close();
            out.flush();
            out.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
