import java.io.*;

class Main {
    // reads input from 'in' and writes the bytes out to 'out'
// START write
public static void echo(InputStream in, OutputStream out) {
    try {
        int c, i = 0;
        byte[] buffer = new byte[1024]; // buffer for bytes
        while((c = in.read()) > -1) {
            if (i < buffer.length)
                buffer[i++] = (byte)c;  // get lowest order byte
            else {
                out.write(buffer);
                out.flush();            // flush output
                i = 0;                  // reset
            }
        }
        // write remaining bytes
        if (i > 0) {
            out.write(buffer, 0, i);
        }
        in.close();                     // close streams
        out.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
// END write
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
