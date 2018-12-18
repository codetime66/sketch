import java.net.Socket;
import java.io.IOException;

class Main {
  public static void main(String[] args) {
    if (args.length != 1) {
        System.out.println("usage: java Main <unreachable host or IPaddr>");
        System.exit(0);
     }
    try {
      Socket sock = new Socket(args[0], 25);
      System.out.println("socket created" + sock.toString());

      sock.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
