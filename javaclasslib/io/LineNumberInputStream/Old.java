import java.io.*;

class Old {
    // reads input from 'in' and writes the bytes out to 'out'
    // with line numbers on the left
    public static void echo(InputStream i, OutputStream o) {
        try {
            LineNumberInputStream in = new LineNumberInputStream(i);
            PrintStream out = new PrintStream(o);
            int c, oldLineNumber = 0, newLineNumber = 0;
            boolean writePrefix = true;

            while((c = in.read()) > -1) {
                if (writePrefix) {
                    out.print(newLineNumber+1);
                    out.write('\t');
                }
                out.write(c);
                if (writePrefix = 
                    ((newLineNumber = in.getLineNumber()) != oldLineNumber))
                    oldLineNumber = newLineNumber;
            }
            in.close();                // close streams
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        try {
            InputStream in;
            if (args.length == 0)
                in = System.in;
            else
                in = new FileInputStream(args[0]);
            echo(in, System.out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
