import java.io.*;

class ScrambleOutputStream extends FilterOutputStream {
    private byte[] p = new byte[4];
    int counter = 0;
    public ScrambleOutputStream (String passwd, OutputStream out) {
        super(out);
        // Compute hash code of password and store into 4 bytes.
        int pw = passwd.hashCode();
        p[0] = (byte) ((pw >>> 24) & 0xFF);
        p[1] = (byte) ((pw >>> 16) & 0xFF);
        p[2] = (byte) ((pw >>> 8) & 0xFF);
        p[3] = (byte) (pw & 0xFF);
    }

    public void write(int b) throws IOException {
        out.write(((byte)b) ^ p[counter++]);
        if (counter == 4)
            counter = 0;
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
            ScrambleOutputStream sout = 
                new ScrambleOutputStream(args[0], out);
            
            byte[] buf = new byte[512];
            int howmany;
            while ((howmany = in.read(buf)) > 0) {
                sout.write(buf, 0, howmany);
            }
            in.close();
            sout.flush();
            sout.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
