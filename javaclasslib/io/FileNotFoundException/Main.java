import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("java Main <inputfile>");
            System.exit(-1);
        }
        try {
            FileInputStream in = new FileInputStream(args[0]);
            // ...
        } catch (FileNotFoundException e) {
            System.err.println("File " + args[0] + " not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
