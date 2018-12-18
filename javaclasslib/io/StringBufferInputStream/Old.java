import java.io.StringBufferInputStream;
import java.io.InputStream;
import java.io.IOException;

class Old {
    private static long rand(InputStream in) {
        long sum = 0;
        try {
            int c;
            while ((c = in.read()) >= 0)
                sum += c;
        } catch (IOException e) {
        }
        return sum;
    }
    public static void main(String[] args) {
        InputStream in;
        // if no string specified in command line, read from standard in
        if (args.length != 1) {
            in = System.in;
        } else {
            in = new StringBufferInputStream(args[0]);
        }
        
        System.out.println(rand(in));
    }
}
