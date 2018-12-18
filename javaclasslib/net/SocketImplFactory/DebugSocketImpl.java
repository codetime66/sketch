package java.net;

import java.io.*;
import java.net.PlainSocketImpl;

/**
 * Wrapper around default socket implementation that
 * provides debugging information.  
 */

public class DebugSocketImpl extends PlainSocketImpl 
{
    protected synchronized void create(boolean stream) throws IOException {
        System.err.println("Creating " + 
                           (stream ? "virtual circuit" : "datagram") +
                           " socket.");
        super.create(stream);
    }

    protected void connect(String host, int port)
        throws UnknownHostException, IOException
    {
        System.err.println("Connecting to " + host + " at port " + port);
        super.connect(host, port);
    }

    protected void connect(InetAddress address, int port) throws IOException {
        System.err.println("Connect to " + address + " at port " + port);
        super.connect(address, port);
    }

    protected synchronized void bind(InetAddress address, int lport) 
        throws IOException
    {
        System.err.println("Binding " + address + " at local port " + lport);
        super.bind(address, lport);
    }

    protected synchronized void listen(int count) throws IOException {
        System.err.println("Listening for " + count + " msec");
        super.listen(count);
    }

    protected synchronized void accept(SocketImpl s) throws IOException {
        System.err.println("Accepting connection for " + s.toString());
        super.accept(s);
    }

    protected synchronized InputStream getInputStream() throws IOException {
        System.err.println("Returning input stream for this socket");
        return super.getInputStream();
    }

    protected synchronized OutputStream getOutputStream() throws IOException {
        System.err.println("Returning output stream for this socket");
        return super.getOutputStream();
    }

    protected synchronized void close() throws IOException {
        System.err.println("Closing socket");
        super.close();
    }

    protected synchronized void finalize() throws IOException {
        System.err.println("Finalizing socket");
        super.finalize();
    }
}
