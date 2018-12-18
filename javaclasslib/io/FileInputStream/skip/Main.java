import java.io.*;

class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Main <input_file>");
            System.exit(-1);
        }
        try {
            File f = new File(args[0]);
            FileInputStream in = new FileInputStream(f);
            long size = f.length();          // Get size of file.

            in.skip(size/2);               // Skip half the file.

            int c;
            while ((c=in.read()) > -1)      // Echo the rest.
                System.out.print((char)c);
            
            in.close();                     // Close it.
            System.out.flush();
        } catch (FileNotFoundException e) {
            System.err.println(args[0] + " is not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
