import java.io.InputStream;
import java.io.IOException;
import java.io.FileInputStream;

class Main {
    public static void wordCount(InputStream in) {
        int bytecount = 0;
        int wordcount = 0;
        int linecount = 0;
        try {
            int c;
            boolean newspace = true;
            while ((c = in.read()) > -1) {
                ++bytecount;
                if (c == '\n' || c == '\r')
                    ++linecount;
                if (Character.isWhitespace((char)c)) {
                    if (newspace) {
                        ++wordcount;
                        newspace = false;
                    }
                } else {
                    newspace = true;
                }
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(linecount + " " + wordcount + " " + bytecount);
    }
    public static void main(String[] args) {
        if (args.length == 1) {
            try {
                wordCount(new FileInputStream(args[0]));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Usage: java Main <file>");
            System.exit(-1);
        }
    }
}
