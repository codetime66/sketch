import java.io.*;

class Main {
    public static void wordCount(Reader in) {
        int charcount = 0;
        int wordcount = 0;
        int linecount = 0;
        try {
            int c;
            boolean newspace = true;
            while ((c = in.read()) > -1) {
                ++charcount;
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
        System.out.println(linecount + " " + wordcount + " " + charcount);
    }
    public static void main(String[] args) {
        if (args.length == 1) {
            try {
                wordCount(new FileReader(args[0]));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Usage: java Main <file>");
            System.exit(-1);
        }
    }
}
