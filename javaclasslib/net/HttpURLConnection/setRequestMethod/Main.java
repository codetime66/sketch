import java.net.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("java Main <request> <http url>");
            System.exit(-1);
        }
        try {
            // Directly use implementation to get 
            HttpImpl conn = new HttpImpl(new URL(args[1]));

            // Set method token
            conn.setRequestMethod(args[0]);

            // Retrieve contents
            Object obj = conn.getContent();

            // Print out header
            for (int i = 0; true; i++) {
                String h = conn.getHeaderField(i);
                String k = conn.getHeaderFieldKey(i);
                if (h == null) {
                    break;
                }
                if (k != null)
                    System.out.print(k + ": ");
                System.out.println(h);
            }

            // Display what we've found
            System.out.println(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
