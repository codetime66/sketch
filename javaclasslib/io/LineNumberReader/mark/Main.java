import java.io.*;

class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Main <inputfile>");
            System.exit(-1);
        }
        try {
            File f = new File(args[0]);
            LineNumberReader in = new LineNumberReader(new FileReader(f));

            int charcount = (int)f.length();
            in.mark(charcount); // mark at beginning of stream
            in.skip(charcount); // skip to end of buffer
            int linecount = in.getLineNumber();

            System.out.println("file:\t" + args[0] +
                               "\nlines:\t" + linecount + 
                               "\nchars:\t" + charcount +
                               "\ncontent:");
            in.reset();        
            char[] buf = new char[100];
            int howmany = in.read(buf);

            // Echo characaters
            for (int i = 0; i < howmany; i++)
                System.out.write(buf[i]);
            System.out.println();

            // Indicate whether there are more
            if (charcount >= 100) 
                System.out.println("...");
            System.out.flush();         // flush output

            in.close();                 // closes all "downstream" readers
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
