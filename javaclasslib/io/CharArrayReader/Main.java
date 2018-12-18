import java.io.*;

class Main {
    public static void main(String[] args) {
        char[] input = { 'a', 'b', 'c', 'd', 'e'};
        CharArrayReader in = new CharArrayReader(input);

        int ch;

        try {
            while ((ch=in.read()) >= 0)        // reads "abcde"
                System.out.print((char)ch);
            in.reset();
            System.out.println();

            in.skip(3);                        // skip "abc"

            while ((ch=in.read()) >= 0)        // reads "de"
                System.out.print((char)ch);
            System.out.println();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
