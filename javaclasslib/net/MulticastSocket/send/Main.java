import java.net.*;
import java.io.*;

class Main {
    public static void main(String[] args) {
        try {
            // Create a multicast address
            InetAddress group = InetAddress.getByName("228.1.2.3");
            int port = 1234;
            MulticastSocket socket = new MulticastSocket();

            // Read from standard input ttl and msg, then send to group
            BufferedReader r = 
                new BufferedReader(new InputStreamReader(System.in));
            String cmd;
            byte ttl = 1; // default 
            while ((cmd=r.readLine()) != null) {
                int where = cmd.indexOf(' ');
                if (Character.isDigit(cmd.charAt(0)) && where > 0) {
                    // Get TTL from input
                    ttl = Byte.parseByte(cmd.substring(0, where));
                    cmd = cmd.substring(where+1);
                }
                DatagramPacket packet = 
                    new DatagramPacket(cmd.getBytes(), cmd.length(), 
                                       group, port);
                socket.send(packet, ttl);
            }
            cmd = "BYE";
            DatagramPacket packet = 
                new DatagramPacket(cmd.getBytes(), cmd.length(),
                                   group, port);
            socket.send(packet, ttl);
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
}
