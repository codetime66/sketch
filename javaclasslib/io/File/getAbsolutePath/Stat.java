import java.io.File;
import java.util.StringTokenizer;
import java.util.NoSuchElementException;

class Stat {
    public static void stat(File f) {
        if (!f.isAbsolute())
            System.out.println("Absolute Pathname:" + f.getAbsolutePath());

        String parent = f.getParent();
        if (parent == null)
            parent = File.separator;
        System.out.println("Directory name:" + parent);

        System.out.println("File name:" + f.getName());

        System.out.println("Type: " +
            (f.isDirectory() ? "directory" :
             (f.isFile() ? "file" : "unknown")));
    }
    public static void components(File f) {
        StringTokenizer parser = 
            new StringTokenizer(f.getAbsolutePath(), File.separator);
        System.out.println("There are " + parser.countTokens() + 
                           " components in the pathname");
        try {
            while(parser.hasMoreTokens()) {
                System.out.println(parser.nextToken());
            }
        } catch (NoSuchElementException e) {
            System.err.println(e);
        }
    }
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Stat <filepath>");
            System.exit(-1);
        }
        File f1 = new File(args[0]);

        if(f1.exists()) {
            stat(f1);
            components(f1);
        } else
            System.err.println(f1.getPath() + " does not exists");
    }
}
