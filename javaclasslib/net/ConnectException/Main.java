import java.net.Socket;
import java.net.InetAddress;
import java.io.IOException;
import java.io.InputStream;

class Main {
  public static void main(String[] args) {
    System.out.println("ConnectionException example");
    try {
      // pick some random port number on local machine
      Socket sock = new Socket(InetAddress.getLocalHost(), 1997);

      InputStream in = sock.getInputStream();

      for (int ch = in.read(); ch >= 0; ch = in.read()) {
        System.out.print((char)(ch));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
