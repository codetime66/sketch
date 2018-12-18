package java.net;

import java.net.*;
import java.io.IOException;
import java.net.PlainDatagramSocketImpl;

/**
 * Wrapper around default datagram socket implementation that
 * provides debugging information.
 */

public class DebugDatagramSocketImpl extends PlainDatagramSocketImpl {
  protected synchronized void create() throws SocketException {
    super.create();
    System.err.println("Created socket using file descriptor " + fd);
  }
  protected synchronized void bind(int lport, InetAddress laddr) 
    throws SocketException {
    System.err.println("Binding to local address: " + laddr + "/" + lport);
    super.bind(lport, laddr);
  }
  protected synchronized void send(DatagramPacket p) throws IOException {
    System.err.println("Sending packet to: " + p.getAddress() + "/" + p.getPort());
    super.send(p);
  }
  protected int peek(InetAddress i) throws IOException {
    int port = super.peek(i);
    System.err.println("Peeking " + i + "/" + port); 
    return port;
  }
  protected synchronized void receive(DatagramPacket p) throws IOException {
    super.receive(p);
    System.err.println("Received packet from " + p.getAddress() + "/" + p.getPort());
  }
  protected void setTTL(byte ttl) throws IOException {
    System.err.println("Setting TTL to " + ttl);
    super.setTTL(ttl);
  }
  protected byte getTTL() throws IOException {
    byte ttl = super.getTTL();
    System.err.println("TTL is " + ttl);
    return ttl;
  }
  protected void join(InetAddress inetaddr) throws IOException {
    System.err.println("Joining " + inetaddr);
    super.join(inetaddr);
  }
  protected void leave(InetAddress inetaddr) throws IOException {
    System.err.println("Leaving " + inetaddr);
    super.leave(inetaddr);
  }
  protected void close() {
    System.err.println("Closing socket");
    super.close();
  }

  /* methods from SocketOptions */
  public void setOption(int optID, Object o) throws SocketException {
    super.setOption(optID, o);
    System.err.println("Set option " + optID + " to " + o);
  }
  public Object getOption(int optID) throws SocketException {
    Object optRes = super.getOption(optID);
    System.err.println("Option " + optID + " is " + optRes);
    return optRes;
  }
}
