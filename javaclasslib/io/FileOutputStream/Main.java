import java.io.FileOutputStream;
import java.io.IOException;

class Main {
    public static void main (String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Main <output_file>");
            System.exit(-1);
        }
        try {
            FileOutputStream out = new FileOutputStream(args[0]);
            FileOutputStream out2 = new FileOutputStream(out.getFD());

            byte[] buf = {'h', 'e', 'l', 'l', 'o', '\n'};
            out.write(buf);
	    out2.write(buf);
            out.close();    // closes out2 too    
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
