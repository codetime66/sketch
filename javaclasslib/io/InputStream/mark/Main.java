import java.io.InputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.BufferedInputStream;

class Main {
    // a modified of URLConnection.guessContentTypeFromStream()
// START mark
static protected String guessContentType(InputStream is) 
    throws IOException {
    if (is.markSupported()) {
        is.mark(6);
        int c1 = is.read();
        int c2 = is.read();
        int c3 = is.read();
        int c4 = is.read();
        int c5 = is.read();
        int c6 = is.read();
        is.reset();
        if (c1 == 'G' && c2 == 'I' && c3 == 'F' && c4 == '8')
            return "image/gif";
        if (c1 == '#' && c2 == 'd' && c3 == 'e' && c4 == 'f')
            return "image/x-bitmap";
        if (c1 == '!' && c2 == ' ' && c3 == 'X' && 
            c4 == 'P' && c5 == 'M' && c6 == '2')
            return "image/x-pixmap";
        if (c1 == '<')
            if (c2 == '!' || (c6 == '>'
            && (c2 == 'h' && (c3 == 't' && c4 == 'm' && c5 == 'l' ||
                              c3 == 'e' && c4 == 'a' && c5 == 'd')
                || c2 == 'b' && c3 == 'o' && c4 == 'd' && c5 == 'y')))
            return "text/html";
    }
    return null;
}
// END mark
// START skip
static protected void skippy(InputStream in)
{
    try {
        int c;
        while ((c = in.read()) > -1) {
            System.out.print((char)c);
            if (in.skip(1) == 0)
                break;
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
// END skip

public static void main(String[] args) {
    if (args.length != 1) {
        System.err.println("Usage: java Main <file>");
        System.exit(-1);
    }
    try {
        InputStream in;
        System.out.println(guessContentType(
            in = new BufferedInputStream(new FileInputStream(args[0]))));
        skippy(in);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}
