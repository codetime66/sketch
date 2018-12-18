import java.io.*;
import java.util.Vector;

class Main {
    static int countOne = 0;
    static int countTwo = 0;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("usage: java Main <input>");
            System.exit(-1);
        }
        try {
            PushbackInputStream pushin = new PushbackInputStream(
                new FileInputStream(args[0]));                       
            boolean eof;
            while (true) {
                if (eof = readGroupOne(pushin))
                    break;
                if (eof = readGroupTwo(pushin))
                    break;
            }
            System.out.println("ones: " + countOne + " twos: " + countTwo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static boolean readGroupOne(PushbackInputStream in) {
        int c = -1;
        int i = 0;
        try {
            c = in.read();
            for (i = 0; c >= 0; i++) {
                if (c < 128) {
                    in.unread(c);
                    break;
                } 
                c = in.read();
            }
        } catch (IOException e) {
        }
        countOne += i;
        return (c == -1);
    }
    private static boolean readGroupTwo(PushbackInputStream in) {
        int c = -1;
        int i = 0;
        try {
            c = in.read();
            for (i = 0; c >= 0; i++) {
                if (c >= 128) {
                    in.unread(c);
                    break;
                } 
                c = in.read();
            }
        } catch (IOException e) {
        }
        countTwo += i;
        return (c == -1);
    }
}
