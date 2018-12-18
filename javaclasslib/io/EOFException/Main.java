import java.io.RandomAccessFile;
import java.io.EOFException;
import java.io.IOException;

class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("java Main <outputfile>");
            System.exit(-1);
        }
        try {
            RandomAccessFile raf = new RandomAccessFile(args[0], "rw");
            String str = "This is a test";
            raf.writeChars(str);
            raf.close();

            // read the stuff back
            raf = new RandomAccessFile(args[0], "r");
            try {
                while (true)
                    System.out.print(raf.readChar());
            } catch (EOFException e) {
                // end of file reached
                System.out.println();
            }
            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
