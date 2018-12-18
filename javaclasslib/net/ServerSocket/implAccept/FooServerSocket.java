import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

class FooSocket extends Socket {
    FooSocket() {
        // Do some FooSocket-specific things.
    }
}
class FooServerSocket extends ServerSocket {
    public FooServerSocket(int port) throws IOException {
        super(port);
    }

  public Socket accept() throws IOException {
      // Create a new FooSocket for each accept.
      FooSocket s = new FooSocket();

      // Pass newly created socket to use for accepting connection.
      implAccept(s);

      // Return connected socket.
      return s;
    }
}
