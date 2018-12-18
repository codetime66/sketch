import java.io.*;

class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Main <input_file>");
            System.exit(-1);
        }
        try {
            char[] buf = {'h', 'h', 'e', 'e', 'l', 'l', 'l', 'l', 
                          'o', 'o', '\n', '\n'};
            SkipFilterReader s1 = 
                new SkipFilterReader(new CharArrayReader(buf));

            int howmany;
            if (s1.markSupported()) {
                System.out.println("marking");
                s1.mark(200);
            }
            int ch;
            while ((ch=s1.read()) >= 0) {
                System.out.print((char)ch);
            }
            if (s1.markSupported()) {
                System.out.println("resetting");
                s1.reset();
            }
            while ((ch=s1.read()) >= 0) {
                System.out.print((char)ch);
            }
            s1.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
