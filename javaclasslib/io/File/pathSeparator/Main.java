import java.io.File;
import java.util.StringTokenizer;
import java.util.NoSuchElementException;

class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Main <path>");
            System.exit(-1);
        }
        StringTokenizer parser = 
            new StringTokenizer(args[0], File.pathSeparator);
        System.out.println("\nInput: " + args[0]);
        System.out.println("There are " + parser.countTokens() + 
                           " entries in the path");
        try {
            while(parser.hasMoreTokens()) {
                System.out.println(parser.nextToken());
            }
        } catch (NoSuchElementException e) {
            System.err.println(e);
        }
    }
}
