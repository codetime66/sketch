import java.io.*;

class Main {
    public static void main(String[] args) {
        char[] input = { 'a', 'b', 'c', 'd', 'e'};
        CharArrayReader in = new CharArrayReader(input);

        int ch;

        try {
            in.skip(3);                        // skip "abc"
            in.mark(0);
            while ((ch=in.read()) >= 0)        // reads "de"
                System.out.print((char)ch);
            in.reset();
            System.out.println();

            while ((ch=in.read()) >= 0)        // reads "de"
                System.out.print((char)ch);
            System.out.println();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
