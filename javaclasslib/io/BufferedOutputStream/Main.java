import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

class Main {
    public static void main(String[] args) {
        try {
            //  9 == 'discard' port
            Socket sock = new Socket("localhost", 9);

            OutputStream so = sock.getOutputStream();
            BufferedOutputStream out = new BufferedOutputStream(so, 8192);

            String msg = "this is a test";
            byte[] ob = msg.getBytes();

            for (int i = 0; i < 5000; i++)
                out.write(ob, 0, ob.length);

            out.flush();
            out.close();
            sock.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
