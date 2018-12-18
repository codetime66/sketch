import java.io.SequenceInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Vector;

class Main {
    // This implements a form of 'cat' that echoes all files named
    // on command line to standard output
    public static void main(String[] args) {
        try {
            Vector streams = new Vector(args.length);
            for (int i = 0; i < args.length; i++)
                streams.addElement(new FileInputStream(args[i]));

            SequenceInputStream in = 
                new SequenceInputStream(streams.elements());

            byte[] b = new byte[256];
            int howmany;
            while ((howmany = in.read(b)) > 0)
                for(int i = 0; i < howmany; i++)
                    System.out.print((char)b[i]);
            System.out.flush();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
