import java.io.*;

class Main {
    public static void echo(Reader i, Writer o) {
        try {
            LineNumberReader in = new LineNumberReader(i);
            PrintWriter out = new PrintWriter(o);
            int c;

            while((c = in.read()) > -1) {
                    out.print(in.getLineNumber());
                    out.write('\t');
                    out.write(c);
                    out.write('\n');
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
