import java.util.Vector;
import java.io.*;

class Main {
    public static char[] concat(Vector objs, boolean printSize) {
        CharArrayWriter out = new CharArrayWriter();

        for (int i = 0; i < objs.size(); i++) {
            String str = objs.elementAt(i).toString();
            out.write(str, 0, str.length());
        }
        if (printSize)
            System.out.println("Size: " + out.size());
        return (out.toCharArray());
    }
    public static void main(String[] args) {
        Vector objs = new Vector(args.length);

        for (int i = 0; i < args.length; i++)
            objs.addElement(args[i]);

        char[] all = concat(objs, true); // print size of total string

        for (int i = 0; i < all.length; i++)
            System.out.print(all[i]);
        System.out.println();
    }
}
