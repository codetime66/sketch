import java.io.File;
import java.util.Date;

class Main {
    public static void printOne(File f) {
        if (f.exists()) {
            System.out.print(f.canRead() ? "r" : "-");
            System.out.print(f.canWrite() ? "w" : "-");
            System.out.print(f.isDirectory() ? "x" : "-");
            System.out.print('\t');

            System.out.print(f.length());
            System.out.print('\t');

            System.out.print(new Date(f.lastModified()));
            System.out.print('\t');
        } else {
            System.out.print("\t\t\t\t\t");
        }
        System.out.println(f.getName());
    }
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Main <filepath>");
            System.exit(-1);
        }
        File f1 = new File(args[0]);
        String[] ls;
        int i;
        for (ls = f1.list(), i = 0; 
             ls != null && i < ls.length;
             printOne(new File(f1, ls[i])), i++);
    }
}
