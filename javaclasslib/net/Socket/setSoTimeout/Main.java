import java.net.Socket;
import java.net.ServerSocket;
import java.net.InetAddress;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.Date;

class ClockServer extends Thread {
    private ServerSocket srvSock = null;
    public ClockServer(int port) {
        super();
        try {
            srvSock = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void run() {
        if (srvSock == null)
            return;
        while (true) {
            try {
	        this.sleep(1000);  // process every 1 seconds

// START nodelay
Socket sock = srvSock.accept();

System.out.println("Socket's original nodelay setting:" +
		   (sock.getTcpNoDelay() ? "on" : "off"));

// enable Tcp no-delay (disable Nagle's algorithm)
sock.setTcpNoDelay(true);  

System.out.println("Socket's updated nodelay setting:" +
		   (sock.getTcpNoDelay() ? "on" : "off"));

OutputStream out = sock.getOutputStream();
String answer = (new Date()).toString();
byte[] b = answer.getBytes();
out.write(b, 0, b.length);
out.write('\n');
out.flush();
sock.close();
// END nodelay

	    } catch (IOException e) {
                e.printStackTrace();
	      } catch (InterruptedException e) {
		System.err.println("woken up...");
		e.printStackTrace();
	      }
        }
    }
    protected void finalize() {
        if (srvSock != null) {
            try {
                srvSock.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            srvSock = null;
        }
    }
}

class Main {
  static int date_port = 1258;        
  
// START main2
  public static void echoer(InetAddress dst, int port) {
    try {
      Socket client = new Socket(dst, port);
      InputStream in = client.getInputStream();
      
      for (int ch = in.read(); ch > 0; ch = in.read()) {
	System.out.print((char)ch);
      }
      client.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
// END main2
  
// START timeout
  public static void impatientEchoer(InetAddress dst, int port, 
				     int timeout, int retries) {
    try {
      Socket client = new Socket(dst, port);

      client.setSoTimeout(timeout); // set socket timeout period
      System.out.println("Setting maximum waiting period to " +
			 client.getSoTimeout() + "ms");
      InputStream in = client.getInputStream();

      for (int i=0; i <retries; i++) {
	try {	
	  for (int ch = in.read(); ch > 0; ch = in.read()) {
	    System.out.print((char)ch);
	  }
	  break;
	} catch (InterruptedIOException timedout) {
	  System.err.println("Timedout on try " + i);
	  // try again, socket and stream are still valid
	}
      }
      client.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
// END timeout
  

// START linger
  public static void lingerEchoer(InetAddress dst, int port, int lingerTimeout) {
    try {
      Socket client = new Socket(dst, port);
      
      System.out.println("Original linger option: " + client.getSoLinger() + "ms");

      // enable linger-on-close option and set linger timeout period
      client.setSoLinger(true, lingerTimeout); 

      System.out.println("Updated linger option: " + client.getSoLinger() + "ms");

      InputStream in = client.getInputStream();

      for (int ch = in.read(); ch > 0; ch = in.read()) {
	System.out.print((char)ch);
      }

      // close is affected by setting of linger-on-close above
      client.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
// END linger
  
  public static void main(String[] args) {
    ClockServer srv = new ClockServer(date_port);
    srv.setDaemon(true);
    srv.start();
    
    try {
      String msg = "\n";
      InetAddress dst = InetAddress.getLocalHost();

      System.out.println("impatient echoer");
      for (int i = 0; i < 5; i++) {
	// wait for 500ms, try 5 times
	impatientEchoer(dst, date_port, 500, 5);
      }

      System.out.println("linger echoer");
      for (int i = 0; i < 5; i++) {
	lingerEchoer(dst, date_port, 500);
      }

      System.out.println("plain echoer");
      for (int i = 0; i < 5; i++) {
	echoer(dst, date_port);
      }
      
      // wait to see some timeouts
//	    Thread.currentThread().sleep(5000);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

