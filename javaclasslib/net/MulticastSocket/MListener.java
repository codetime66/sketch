import java.net.*;
import java.io.*;
import java.util.Date;

public class MListener extends Thread {
    MulticastSocket msocket;
    InetAddress group;

    public MListener(String g, int port) {
        super();
        try {
            msocket = new MulticastSocket(port);
            group = InetAddress.getByName(g);
            msocket.joinGroup(group);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public void run() {
        while (true) {
            try {
                byte[] buf = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                msocket.receive(packet);

                String answer = new String(packet.getData(), 0,
                                           packet.getLength());
                System.out.println(new Date() + ":(" + 
                                   answer.length() + ")" + answer);
                if (answer.equals("BYE")) {
                    msocket.leaveGroup(group); // Not necessary; for demo only
                    msocket.close();           // close() will also leave group
                    System.out.println("exiting...");
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            MListener m = new MListener("228.1.2.3", 1234);
            m.start();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
