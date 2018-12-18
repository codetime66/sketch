import java.io.*;

class New {
    // reads input from 'in' and writes the characters out to 'out'
    // with line numbers on the left
    public static void echo(Reader i, Writer o) {
        try {
            LineNumberReader in = new LineNumberReader(i);
            PrintWriter out = new PrintWriter(o);
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
            Reader in;
            if (args.length == 0)
                in = new InputStreamReader(System.in);
            else
                in = new FileReader(args[0]);
            echo(in, new OutputStreamWriter(System.out));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
