import java.math.*;
import java.io.*;

class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: java Main <in-radix> <out-radix>");
            System.exit(1);
        }

        int inradix = Integer.parseInt(args[0]);
        int outradix = Integer.parseInt(args[1]);

        BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
        String line;
        try {
            // Read numbers from standard input.
            while ((line = is.readLine()) != null) {
                BigInteger a = new BigInteger(line, inradix);
                System.out.println(a.toString(outradix));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
