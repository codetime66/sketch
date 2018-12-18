import java.io.*;

class Main {
    private static long rand(Reader in) {
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
        Reader in;
        // if no string specified in command line, read from standard in
        if (args.length != 1) {
            in = new InputStreamReader(System.in);
        } else {
            in = new StringReader(args[0]);
        }
        
        System.out.println(rand(in));
        try {
            in.close();
        } catch (IOException e) {}
    }
}
