import java.net.ServerSocket;
import java.io.IOException;

class Main {
  public static void main(String[] args) {
    System.out.println("BindException example");
    try {
      // create server socket for port 2040
      ServerSocket srv = new ServerSocket(2040, 50);
      System.out.println("server socket created" + srv.toString());

      // create another one for same port will throw BindException
      ServerSocket srv2 = new ServerSocket(2040, 50);
      System.out.println("server socket created" + srv2.toString());

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
