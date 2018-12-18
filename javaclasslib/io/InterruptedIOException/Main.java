import java.io.*;
import java.net.*;

class Main {
    public static void main(String[] args) {
        try {
            DatagramSocket sock = new DatagramSocket(); // any port
            DatagramPacket dg = new DatagramPacket(new byte[128], 128);
            
            sock.setSoTimeout(500);
            sock.receive(dg);
        } catch (InterruptedIOException e) {
            System.out.println("interrupted IO: " + e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
