import java.net.*;
import java.io.*;
import java.util.Date;

class Main {
    static final int date_port = 1258;  // pick a port that server can use

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
        // start server
        ClockServer srv = new ClockServer(date_port);

        try {
            InetAddress dst = InetAddress.getLocalHost();

            for (int i = 0; i < 10; i++)
                getDate(dst, date_port);
        } catch (UnknownHostException e) {
                System.err.println("Host not found: " + e);
        }
    }
}

// Server spawns worker thread to handle each connection
class ClockServer extends Thread {
    private ServerSocket srvSock = null;
    public ClockServer(int port) {
        try {
            srvSock = new ServerSocket(port, 5); // backlog of 5
            System.err.println("Server Socket: " + srvSock.toString());
            System.err.println("Socket is connected to: " + 
                               srvSock.getInetAddress());
            System.err.println("Local port: " + srvSock.getLocalPort());
            // Set thread to be "daemon" so that the program can exit
            // without having to kill the thread.
            setDaemon(true);
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void run() {
        Socket sock;
        while (true) {
            try {
                sock = srvSock.accept();
                (new ClockWorker(sock)).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Override finalize() to close server socket
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

// Worker that determines date and sends back to server
class ClockWorker extends Thread {
    private Socket sock;
    public ClockWorker(Socket ss) {
        super();
        sock = ss;
    }
    public void run() {
        try {
            BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(sock.getOutputStream()));
            String answer = Thread.currentThread().getName() + ": " +
                (new Date()).toString() + "\n";
            out.write(answer, 0, answer.length());
            out.flush();
            sock.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
