import java.io.*;

class Main {
    // reads input from 'in' and writes the bytes out to 'out'
    public static void echo(InputStream in, OutputStream out) {
        try {
            int c;
            while((c = in.read()) > -1)
                out.write(c);
            out.flush();               // flush output

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
