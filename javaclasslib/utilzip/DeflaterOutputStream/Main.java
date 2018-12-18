import java.awt.*;
import java.io.*;

class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java Main <imagefile>...");
            System.exit(1);
        }

        for (int i=0; i<args.length; i++) {
            try {
                ObjectOutputStream os = new ObjectOutputStream(
                    new FileOutputStream(args[i]+".imgz"));
    
                System.out.println("Writing " + args[i] + ".imgz");
                os.writeObject(new SerImage(args[i]));
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.exit(0);
    }
}
