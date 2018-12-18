import java.io.*;

class Main {
    private static void preview(Reader in) {
        try {
            if (in.markSupported()) {
                in.mark(0);                    // readlimit ignored
                char[] buf = new char[5];
                in.read(buf);                  // read characters
                in.reset();                    // reset to beginnning
                System.out.println("preview: " + new String(buf));
            }
        } catch (IOException e) {
        }
    }
    private static void showAll(Reader in) {
        try {
            char[] buf = new char[512];
            int howmany;
            while ((howmany=in.read(buf)) > 0) {
                System.out.print(new String(buf, 0, howmany));
            }
        } catch (IOException e) {
        }
    }
    public static void main(String[] args) {
        Reader in;
        // if no string specified in command line, read from standard in
        if (args.length != 1) {
            in = new InputStreamReader(System.in);
        } else {
            in = new StringReader(args[0]);
        }
        preview(in);
        showAll(in);
        try {
            in.close();
        } catch (IOException e) {}
    }
}
