import java.io.*;

class Main {
    // Reads input from 'in' and writes the characters out to 'out'
    // with line numbers on the left
    public static void echo(Reader i, Writer o) {
        try {
            LineNumberReader in = new LineNumberReader(i);
            PrintWriter out = new PrintWriter(o);
            String str;

	    // Subtract one because readLine() already incremented line no.
            while((str = in.readLine()) != null) {
                out.println(in.getLineNumber()-1 + "\t" + str);
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
