import java.net.*;
import java.io.*;


class Main {
// START tostr
public static void socketDetails(Socket s) {
    System.out.println("Socket description " + s.toString());
    System.out.println("Destination: " + s.getInetAddress() + "/" + s.getPort());
    System.out.println("Source: " + s.getLocalAddress() + "/" + s.getLocalPort());
}
// END tostr
// START output
// write string to socket
public static void writeToSocket(Socket s, String msg) {
    try {
	OutputStream out = s.getOutputStream();
	if (out != null) {
	    byte[] ob = msg.getBytes();
	    out.write(ob);
	    out.flush();
	    // close() will happen when socket gets closed
	}
    } catch (IOException e) {
	System.err.println("Had problems with writing to socket: " + e);
    }
}
// END output

    public static void main(String[] args) {
/*
// START factory
try {
    Socket.setSocketImplFactory(new DebugSocketImplFactory());
} catch (IOException e) {
    System.out.println("Cannot set Socket factory: " + e);
}
// END factory
*/
    if (args.length != 1) {
	System.err.println("java Main <hostname>");
	System.exit(-1);
    }
    String target = args[0];

// START socket
try {
    // Connect to hostname, port = 9 ('discard' port)
    Socket sock = new Socket(target, 9);
    socketDetails(sock);
    writeToSocket(sock, "this is a test");
    sock.close();

    // This time use InetAddr
    InetAddress dstAddr = InetAddress.getByName(target);
    Socket sock2 = new Socket(dstAddr, 9);
    socketDetails(sock2);
    writeToSocket(sock2, "this is a test");
    sock2.close();

    // This time specify local address and local port to use
    InetAddress localAddr = InetAddress.getLocalHost();  // pick any
    int localPort = 1500;
    Socket sock3 = new Socket(dstAddr, 9, localAddr, localPort);
    socketDetails(sock3);
    writeToSocket(sock3, "this is a test");
    sock3.close();

    // Do the same thing using a host name instead of InetAddr
    Socket sock4 = new Socket(target, 9, localAddr, 2001);
    socketDetails(sock4);
    writeToSocket(sock4, "this is a test");
    sock4.close();
} catch (IOException e) {
    e.printStackTrace();
}
// END socket
}
}
