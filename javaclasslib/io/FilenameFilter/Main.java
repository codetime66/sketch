import java.io.FilenameFilter;
import java.io.File;

class JavaSrcFilter implements FilenameFilter {
    public boolean accept(File dir, String name) {
        return (name.endsWith(".java"));
    }
}

class Main {
    public static void main (String[] args) {
        String dir = ".";
        if (args.length == 1)
            dir = args[0];
        
        File f1 = new File(dir);
        int i;
        String[] ls;
        FilenameFilter filter = new JavaSrcFilter();
        System.out.println("Java Source Files: " );
        for (ls = f1.list(filter), i = 0; 
             ls != null && i < ls.length;
             System.out.println("\t" + ls[i++]));
    }
}
