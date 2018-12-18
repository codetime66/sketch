import java.io.PrintStream;
import java.util.Date;

class Main {
    public static void main(String[] args) {
        String str = "abc";
        char[] chs = new char[str.length()];
        str.getChars(0, str.length(), chs, 0);

        System.out.print(new Date());          // Printing objects
        System.out.println(new Date());        // date

        System.out.print(str);                 // String
        System.out.println(str);
        System.out.print(chs);                 // char[]
        System.out.println(chs);
        System.out.print(' ');                 // char
        System.out.println(' ');
        System.out.print(5);                   // int
        System.out.println(5); 
        System.out.print(5L);                  // long
        System.out.println(5L);
        System.out.print(1.23f);               // float
        System.out.println(1.23f);
        System.out.print(1.23);                // double
        System.out.println(1.23); 
        System.out.print(true);                // boolean
        System.out.println(true); 

        // flush stream and check if we got any errors from those
        // print() and println() calls
        if (System.out.checkError()) {
            System.err.println("Got errors printing");
            System.exit(-1);
        }

        // can also 'write' to a print stream
        System.out.write('A');
        byte[] b = str.getBytes();
        System.out.write(b, 0, b.length);
        System.out.flush();	// not needed; close() will flush
        System.out.close();
    }
}
