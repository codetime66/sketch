import java.io.FileOutputStream;
import java.io.IOException;

class Main {
    public static void main (String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Main <output_file>");
            System.exit(-1);
        }
        try {
            // create file in append mode
            FileOutputStream out = new FileOutputStream(args[0], true);
            byte[] buf = {'h', 'e', 'l', 'l', 'o', '\n'};
            out.write(buf);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
