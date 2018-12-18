import java.io.*;

class Main {
    public static void main(String[] args) {
        // Create a pushback reader with buf size of 10
        PushbackReader in = new PushbackReader(
            new InputStreamReader(System.in), 10);

        char[] buf = {'n', 'u', 'm', '\u00e9', 'r', 'o'};

        try {
            in.unread(buf);     // next chars read are buf[0], buf[1]
            int c;
            // Echo contents of reader to standard out.
            while ((c=in.read()) >= 0) {
                System.out.print((char)c);      // numero ...
            }
            System.out.println();
        } catch (IOException e) {}
    }
}
