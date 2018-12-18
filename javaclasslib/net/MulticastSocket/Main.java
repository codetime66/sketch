import java.net.*;
import java.io.*;

class Main {
    public static void main(String[] args) {
        try {
            // Create a multicast address
            InetAddress group = InetAddress.getByName("228.1.2.3");
            int port = 1234;
            DatagramSocket socket = new DatagramSocket();

            // Read from standard input and send to group
            BufferedReader r = 
                new BufferedReader(new InputStreamReader(System.in));
            String cmd;
            while ((cmd=r.readLine()) != null) {
                DatagramPacket packet = 
                    new DatagramPacket(cmd.getBytes(), cmd.length(), 
                                       group, port);
                socket.send(packet);
            }
            cmd = "BYE";
            DatagramPacket packet = 
                new DatagramPacket(cmd.getBytes(), cmd.length(),
                                   group, port);
            socket.send(packet);
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
}
