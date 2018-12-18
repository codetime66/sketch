import java.util.Vector;
import java.util.Enumeration;
import java.io.*;

class Main {
// reads in a file of lines and writes the lines out in reverse order
public static void main (String[] args) {
    if (args.length != 2) {
        System.err.println("Usage: Main inputfile outputfile");
        System.exit(1);
    }
    try {
        BufferedReader in = new BufferedReader(new FileReader(args[0]));
        Vector buf = new Vector(100);
        String str;
        while ((str = in.readLine()) != null)
            buf.addElement(str);
        in.close();

        BufferedWriter out = new BufferedWriter(new FileWriter(args[1]));
        for (int i = buf.size()-1; i >=0; i--) {
            str = (String)buf.elementAt(i);
            out.write(str, 0, str.length());
            out.write('\n');
        }
        out.close();
    } catch (IOException e) {
        e.printStackTrace();
    } 
}
}
