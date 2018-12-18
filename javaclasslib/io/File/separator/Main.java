import java.io.File;
import java.util.*;

class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Main <filepath>");
            System.exit(-1);
        }
        String path = (new File(args[0])).getAbsolutePath();
        StringTokenizer parser = new StringTokenizer(path, File.separator);
        System.out.println("\nInput: " + path);
        System.out.println("There are " + parser.countTokens() + 
                           " components in the file pathname");
        try {
            while(parser.hasMoreTokens()) {
                System.out.println(parser.nextToken());
            }
        } catch (NoSuchElementException e) {
            System.err.println(e);
        }
    }
}
