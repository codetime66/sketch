import java.io.*;

// a filter reader that skips every other character in the stream

class SkipFilterReader extends FilterReader {
    public SkipFilterReader (Reader in) {
        super(in);
    }
    public int read() throws IOException {
        synchronized (lock) {
            int c = in.read();
            in.skip(1);
            return (c);
        }
    }
    public int read(char[] b, int off, int len) 
        throws IOException {
            synchronized (lock) {
                char[] tmp = new char[len+len];

                int howmany = in.read(tmp);
                int real_count = off;
                for (int i = 0; i < howmany; i += 2) {
                    b[real_count++] = tmp[i];
                }
                return (real_count);
            }
    }

    public boolean markSupported() {
        return in.markSupported();
    }

    public void mark(int readlimit) throws IOException {
        in.mark(readlimit + readlimit);
    }
}

class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Main <input_file>");
            System.exit(-1);
        }
        try {
            FileReader f1 = new FileReader(args[0]);
            SkipFilterReader s1 = new SkipFilterReader(f1);

            for(int c = s1.read(); c > -1; c = s1.read()) {
                System.out.print((char)c);
            }
            s1.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
