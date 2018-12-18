import java.io.*;

// reads in a file and sends it to standard output 2 times
class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Main <file>");
            System.exit(-1);
        }
        try {
            File f = new File(args[0]);
            FileInputStream in = new FileInputStream(f);
            // create buffered input stream for 'in'
            BufferedInputStream bufin = new BufferedInputStream(in);
            if (bufin.markSupported()) {
                int limit;
                // create mark for size of file
                bufin.mark(limit=(int)f.length());

                // first copy; read just before EOF
                for (int i = 0; i < limit; i++)
                    System.out.print((char)(bufin.read()));
                // reset to beginning of file
                bufin.reset();
            }
            int c;
            while ((c=bufin.read()) >= 0)   // second copy
                System.out.print((char)c);
            bufin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
