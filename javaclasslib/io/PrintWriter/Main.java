import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

class Main {
    public static void main(String[] args) {
        String str = "abc";
        char[] chs = new char[str.length()];
        str.getChars(0, str.length(), chs, 0);

        PrintWriter out;
	try {
	    out = new PrintWriter(new FileWriter(args[0]));
	} catch (IOException e) {
	    System.err.println(e);
	    return;
	}

        out.print(new Date());          // Printing objects
        out.println(new Date());        // date

        out.print(str);                 // String
        out.println(str);
        out.print(chs);                 // char[]
        out.println(chs);
        out.print(' ');                 // char
        out.println(' ');
        out.print(5);                   // int
        out.println(5); 
        out.print(5L);                  // long
        out.println(5L);
        out.print(1.23f);               // float
        out.println(1.23f);
        out.print(1.23);                // double
        out.println(1.23); 
        out.print(true);                // boolean
        out.println(true); 

        // flush and check if we got any errors from those
        // print() and println() calls
        if (out.checkError()) {
            System.err.println("Got errors printing");
            System.exit(-1);
        }

        // can also 'write' to a print writer
        out.write('A');
        out.write(str);
        char[] buf = str.toCharArray();
        out.write(buf, 0, buf.length);
        out.flush();    // not needed; close() will flush
        out.close();
    }
}
