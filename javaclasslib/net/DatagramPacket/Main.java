import java.net.*;
import java.io.*;

class Main {
    public static String dgExchange(String msg, InetAddress dst, int port) {
        byte[] outbuf = msg.getBytes();
        byte[] inbuf = new byte[256];  // default size

        try {
            // Send datagram
            DatagramPacket request = 
                new DatagramPacket(outbuf, outbuf.length, dst, port);
            DatagramSocket sock = new DatagramSocket();
            sock.send(request);

            // Wait for reply
            DatagramPacket reply = new DatagramPacket(inbuf, inbuf.length);
            sock.receive(reply);

            System.out.println(
                "Received packet from:" + reply.getAddress() + 
                " port: " + reply.getPort() +
                " length: " + reply.getLength());

            sock.close();
            return (new String(reply.getData()));
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (null);
    }

    public static void main(String[] args) {
        try {
            String msg = "\n";
            int port = 13;
            InetAddress dst = InetAddress.getLocalHost();

            if (args.length > 0) {
                port = Integer.parseInt(args[0]);
                if (args.length >= 2)
                    msg = args[1];
                if (args.length == 3)
                    dst = InetAddress.getByName(args[2]);
            }

            System.out.println(dgExchange(msg, dst, port));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
