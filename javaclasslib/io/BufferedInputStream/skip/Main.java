import java.io.*;

//  read a file and skips first half, and echos rest
class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Main <file>");
            System.exit(-1);
        }
        try {
            File f = new File(args[0]);
            FileInputStream in = new FileInputStream(f);
            // create buffered input stream with initial buffer
            BufferedInputStream bufin = new BufferedInputStream(in, 1024);
            int count;
            long half = f.length()/2;

            bufin.skip(half);        // skip first half of file

            // echo the rest
            byte[] buf = new byte[1024];
            while ((count=bufin.read(buf, 0, buf.length)) > 0)
                for (int i = 0; i<count; i++)
                    System.out.print((char)buf[i]);

            System.out.flush();
            bufin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
