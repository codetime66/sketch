
import java.net.InetAddress;

class Main {
  public static void main(String[] args) {
    try {
      InetAddress me = InetAddress.getLocalHost();
      System.out.println("hostname: " + me.getHostName());
      System.out.println("address: " + me.getHostAddress());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
