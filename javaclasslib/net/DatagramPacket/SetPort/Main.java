
import java.net.DatagramSocketImpl;
import java.net.InetAddress;
import java.net.DatagramPacket;
import java.net.SocketException;
import java.io.IOException;

class SomeDatagramSocketImpl extends DatagramSocketImpl {
  int fd;
  int senderPort;
  InetAddress senderAddr;
  byte[] incoming;

  private native void receiveImpl() throws IOException;

  protected void receive(DatagramPacket dg) throws IOException {
    receiveImpl();      // sets sender information and incoming buffer

    dg.setPort(senderPort);
    dg.setAddress(senderAddr);
    dg.setData(incoming);       // do this before setLength()
    dg.setLength(incoming.length);
  }

  protected void create() throws SocketException {
  }

  protected void bind(int lport, InetAddress laddr) throws SocketException {
  }

  protected void send(DatagramPacket p) throws IOException {
  }

  protected int peek(InetAddress i) throws IOException {
	throw new IOException();
  }

  protected void setTTL(byte ttl) throws IOException {
  }

  protected byte getTTL() throws IOException {
	throw new IOException();
  }

  protected void join(InetAddress i) throws IOException {
  }

  protected void leave(InetAddress i) throws IOException {
  }

  protected void close() {
  }

  public Object getOption(int i) throws SocketException {
	throw new SocketException();
  }
  public void setOption(int i, Object obj) throws SocketException {
  }
}


class Main {
  public static void main(String[] args) {
    
  }
}
