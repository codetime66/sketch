import java.io.*;
import java.text.*;
import java.util.*;
import java.util.zip.*;

class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java Main <ntsScCed> <zip filename>");
            System.err.println("    n - name");
            System.err.println("    t - modified time");
            System.err.println("    c - comment");
            System.err.println("    C - CRC");
            System.err.println("    s - compressed size");
            System.err.println("    S - size");
            System.err.println("    e - extra");
            System.err.println("    d - is directory");
            System.exit(1);
        } 
        try {
            ZipFile zipfile = new ZipFile(args[1]);

            for (Enumeration e=zipfile.entries(); e.hasMoreElements() ;) {
                printEntry((ZipEntry)e.nextElement(), args[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void printEntry(ZipEntry ze, String options) {
        for (int i=0; i<options.length(); i++) {
            switch (options.charAt(i)) {
              case 'n':
                System.out.print(ze.getName());
                break;
              case 't':
                System.out.print(DateFormat.getDateTimeInstance(
                    DateFormat.SHORT, 
                    DateFormat.SHORT).format(new Date(ze.getTime())));
                break;
              case 'c':
                System.out.print(ze.getComment());
                break;
              case 'C':
                System.out.print(ze.getCrc());
                break;
              case 's':
                System.out.print(ze.getCompressedSize());
                break;
              case 'S':
                System.out.print(ze.getSize());
                break;
              case 'e':
                byte[] extra = ze.getExtra();
                for (int j=0; j<extra.length; j++) {
                    System.out.println(Integer.toHexString(extra[j]));
                }
                break;
              case 'd':
                System.out.print(ze.isDirectory());
                break;
            }
            if (i < options.length() - 1) {
                System.out.print('\t');
            }
        }
        System.out.println();
    }
}
