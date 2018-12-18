import java.io.*;

class Main {
    public static void main(String[] args) {
        // Create a pb input stream with buf size of 10
        PushbackInputStream in = new PushbackInputStream(System.in, 10);

        byte[] buf = {'h', 'e', 'l', 'l', 'o', '\n'};
        try {
            in.unread(buf);
            int c;
            // Echo contents of pb stream to standard out.
            while ((c=in.read()) >= 0) {
                System.out.print((char)c);	// hello ...
            }
            System.out.println();
        } catch (IOException e) {}
    }
}
