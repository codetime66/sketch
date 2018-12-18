import java.net.FileNameMap;
import java.util.Hashtable;
import java.io.File;

class TestMap implements FileNameMap {
    static Hashtable map = new Hashtable();
    static {
        // add a few entries for testing
        map.put("txt", "text/plain");
        map.put("text", "text/plain");
        map.put("htm", "text/html");
        map.put("html", "text/html");
        map.put("gif", "image/gif");
        map.put("mpg", "video/mpeg");
        map.put("mpeg", "video/mpeg");
    };

    public String getContentTypeFor(String filename) {
        // first get rid of fragment identifier of URL in case filename is URL
        int posn = filename.lastIndexOf('#');
        if (posn != -1)
            filename = filename.substring(0, posn - 1);

        File f = new File(filename);
        String atom = f.getName();       // get name without directory

        System.out.println("atom: " + atom);
    
        posn = atom.lastIndexOf('.');

        if (posn == -1)
            return null;       // don't know how to deal with no extension

        String typename = atom.substring(posn + 1);

        System.out.println("typename: " + typename);
        return (String) map.get(typename);
    }
}

class Main {
    public static void main(String args[]) {
        String target = "http://www.sun.com/homepage.gif";
        if (args.length > 0) {
              target = args[0];
        }

        FileNameMap map = new TestMap();
        System.out.println("type is " + map.getContentTypeFor(target));
    }
}
