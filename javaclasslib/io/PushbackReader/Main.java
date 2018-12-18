import java.io.*;
import java.util.Vector;

class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Main <input>");
            System.exit(-1);
        }
        Vector vec = new Vector();
        try {
            PushbackReader in = new PushbackReader(new FileReader(args[0]));
            boolean eof;
            while (true) {
                if (eof = readNumber(in, vec))
                    break;
                if (eof = readString(in, vec))
                    break;
            }
            in.close();
        } catch (IOException e) {
            System.err.println(e);
        }
        System.out.println(vec);
    }
    private static boolean readNumber(PushbackReader in, Vector vec) {
        StringBuffer sb = new StringBuffer();
        int c = -1;
        // read number
        try {
            for (c = in.read(); c >= 0; c = in.read()) {
                if (Character.isDigit((char)c)) {
                    sb.append((char)c);
                } else if (Character.isWhitespace((char )c)) {
                    break;
                } else {
                    in.unread(c);
                    break;
                }
            }
        } catch (IOException e) {
        }
        if (sb.length() > 0)
            vec.addElement(Integer.valueOf(sb.toString()));
        return (c == -1);
    }
    private static boolean readString(PushbackReader in, Vector vec) {
        StringBuffer sb = new StringBuffer();
        int c = -1;
        try {
            for (c = in.read(); c >= 0; c = in.read()) {
                if (Character.isDigit((char)c)) {
                    in.unread(c);
                    break;
                } else if (Character.isWhitespace((char )c))
                    break;
                else
                    sb.append((char)c);
            }
        } catch (IOException e) {
        }
        vec.addElement(sb.toString());
        return (c == -1);
    }
}
