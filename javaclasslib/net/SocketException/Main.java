import java.net.MulticastSocket;
import java.io.IOException;

class Main {
    public static void main(String[] args) {
        try {
            // Set the time-to-live to something outrageous.
            MulticastSocket sock = new MulticastSocket();
            sock.setTTL((byte)127); // OK
            sock.setTTL((byte)128); // Not OK (arg is treated as unsigned)
            sock.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
