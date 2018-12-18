import java.io.File;

class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java Main <file1> <file2>");
            System.exit(-1);
        }
        File f1 = new File(args[0]);
        File f2 = new File(args[1]);
        System.out.println("f1: " + f1.toString() + " " + f1.hashCode());
        System.out.println("f2: " + f2.toString() + " " + f2.hashCode());

        if (f1.equals(f2)) {
            System.err.println("Cannot rename a file to itself");
            System.exit(-1);
        }
        System.out.println(f1.getPath() + 
           (f1.renameTo(f2) ? " renamed to " : " could not be renamed to ") +
                           f2.getPath());

        // check f1 and f2: their path and hash codes 
        System.out.println("f1: " + f1.toString() + " " + f1.hashCode());
        System.out.println("f2: " + f2.toString() + " " + f2.hashCode());
    }
}
