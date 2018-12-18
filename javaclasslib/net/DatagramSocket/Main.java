import java.net.*;
import java.io.*;

// START main1
class EchoServer extends Thread {
    private DatagramSocket sock = null;
    EchoServer(int port) {
	try {
	    sock = new DatagramSocket(port);
	} catch (SocketException e) {
	    e.printStackTrace();
	}
    }
    public void run() {
	if (sock == null)
	    return;

	byte[] inbuf = new byte[1024];
	DatagramPacket request = new DatagramPacket(inbuf, inbuf.length);
	try {
	    while (true) {
		sock.receive(request);
		sock.send(request); // just return what was sent
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
// END main1

class Main {
    static int echo_port = 1257;

// START main2
public static void echo(String msg, InetAddress dst, int port) {
    byte[] inbuf = new byte[1024];  // default size
    byte[] outbuf = msg.getBytes();

    try {
	DatagramSocket client = new DatagramSocket(); // any port
	DatagramPacket request = new DatagramPacket(outbuf, outbuf.length,
	    dst, port);
	DatagramPacket reply = new DatagramPacket(inbuf, inbuf.length);
	client.send(request);
	client.receive(reply);
	client.close();
	System.out.println(new String(reply.getData()));
    } catch (SocketException e) {
	e.printStackTrace();
    } catch (IOException e) {
	e.printStackTrace();
    }
}
// END main2 

    public static void main(String[] args) {
	if (args.length != 1) {
	    System.err.println("Usage: java Main <message>");
	    System.exit(1);
	}

	// start server
	EchoServer srv = new EchoServer(echo_port);
	srv.setDaemon(true);
	srv.start();

	try {
	    String msg = "\n";
	    InetAddress dst = InetAddress.getLocalHost();

	    echo(args[0], dst, echo_port);

	} catch (UnknownHostException e) {
	    System.err.println("Host not found: " + e);
	}

// START local
try {
    DatagramSocket client = new DatagramSocket();
    System.out.println("Using port number " + client.getLocalPort());
    System.out.println("Using address " + client.getLocalAddress());
// UNCOM	...
    client.close();
} catch (SocketException e) {
    e.printStackTrace();
}
// END local
    }
}



