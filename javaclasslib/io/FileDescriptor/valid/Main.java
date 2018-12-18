import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileDescriptor;

class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Main <input_file>");
            System.exit(-1);
        }
        try {
            FileInputStream in = new FileInputStream(args[0]);
            if (in.getFD().valid())
                System.out.println("got valid file descriptor");
            // ...

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
