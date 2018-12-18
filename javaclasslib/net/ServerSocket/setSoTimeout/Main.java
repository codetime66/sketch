import java.net.*;
import java.io.*;
import java.util.Date;

class ClockServer extends Thread {
    private ServerSocket srvSock = null;
    public ClockServer(int port, int timeout) {
        super();
        try {
            srvSock = new ServerSocket(port, 5); // backlog = 5
            // set timeout for future accept() calls;
            srvSock.setSoTimeout(timeout);         
            setDaemon(true);
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void run() {
        while (true) {
            try {
                Socket sock = srvSock.accept();
                BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(sock.getOutputStream()));
                String answer = Thread.currentThread().getName() + ": " +
                    (new Date()).toString() + "\n";
                out.write(answer, 0, answer.length());
                out.flush();
                sock.close();
            } catch (InterruptedIOException e) {
                try {
                    System.err.println("No requests for: " + 
                                       srvSock.getSoTimeout() + "ms.");
                } catch (IOException et) {
                  // exception from getSoTimeOut()
                    et.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    // Close socket when done
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

    public static void getDate(InetAddress dst, int port) {
        try {
            Socket client = new Socket(dst, port);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(client.getInputStream()));
            System.out.println(in.readLine());
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // start server with timeout of 1 sec
        ClockServer srv = new ClockServer(date_port, 1000);
        try {
            InetAddress dst = InetAddress.getLocalHost();

            for (int i = 0; i < 10; i++)
                getDate(dst, date_port);

            // wait to see some timeouts
            Thread.currentThread().sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
