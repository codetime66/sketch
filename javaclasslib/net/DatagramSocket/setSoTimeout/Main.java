import java.net.*;
import java.io.*;

// START timeout
class TimedEchoServer extends Thread {
    private DatagramSocket sock = null;
    TimedEchoServer(int port) {
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
	    sock.setSoTimeout(1000);	// set timeout to be 1 second
	
	    while (true) {
	        try {
		    sock.receive(request);
		    sock.send(request); 	// just return what was sent
	        } catch (InterruptedIOException e) {
		    System.err.println("No message for " + 
				        sock.getSoTimeout() + "ms");
		}
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
// END timeout

class Main {
    static int echo_port = 1257;

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

    public static void main(String[] args) {
	if (args.length != 1) {
	    System.err.println("Usage: java Main <message>");
	    System.exit(1);
	}

	// start server
	TimedEchoServer srv = new TimedEchoServer(echo_port);
	srv.setDaemon(true);
	srv.start();

	try {
	    String msg = "\n";
	    InetAddress dst = InetAddress.getLocalHost();

		// sleep for 2 seconds before calling server
	    Thread.currentThread().sleep(2000);
	    echo(args[0], dst, echo_port);

	} catch (UnknownHostException e) {
	    System.err.println("Host not found: " + e);
	} catch (InterruptedException e) {
	    System.err.println("Interrupted!");
	}
    }
}



