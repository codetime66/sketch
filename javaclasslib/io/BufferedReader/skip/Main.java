import java.io.*;

// Second half first
class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Main <inputfile>");
            System.exit(-1);
        }
        try {
            File f = new File(args[0]);
            BufferedReader in = new BufferedReader(new FileReader(f));
            if (!in.markSupported()) {
                System.err.println("Mark not supported");
                System.exit(-1);
            }
            long limit  = f.length();        // get file size
            // create mark for size of file
            in.mark((int)limit);

            // first copy; read just before EOF
            in.skip(limit/2);
            char[] buf = new char[(int)(limit/2)+1];
            int howmany = in.read(buf);
            PrintWriter out = new PrintWriter(System.out, true);
            out.write(buf, 0, howmany);

            // reset to beginning of file
            in.reset();
            howmany = in.read(buf, 0, (int)limit/2);
            out.write(buf, 0, howmany);
            out.flush();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
