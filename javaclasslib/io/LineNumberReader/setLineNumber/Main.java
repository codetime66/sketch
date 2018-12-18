import java.io.*;

class Main {
    public static void echo(Reader i, OutputStream o, int pagesize) {
        try {
            LineNumberReader in = new LineNumberReader(i);
            PrintWriter out = new PrintWriter(o);
            int lineno;

            String str;

            // Subtract one because readLine() already incremented line no.
            while((str = in.readLine()) != null) {
                lineno = in.getLineNumber();
                out.println(lineno-1 + "\t" + str);
                if (lineno >= pagesize) {
                    in.setLineNumber(0);
                }
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
            echo(in, System.out, 10);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
