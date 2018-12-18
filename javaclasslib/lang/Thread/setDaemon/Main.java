import java.net.*;
class Main {
    public static void main(String[] args ) {
        (new Waiter()).start();

        try {
            Thread.sleep(5000);    // Wait 5 seconds.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Waiter extends Thread {
    Waiter() {
        setDaemon(true);
    }

    public void run() {
        try {
            ServerSocket socket = new ServerSocket(2000);
            while (true) {
                Socket s = socket.accept();
                System.out.println("got a connection");
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
