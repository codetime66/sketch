// class that prints out "Lights On!" every second
class ThreadTest extends Thread {
    public void run() {
        while (true) {
            System.out.println("Lights On!");
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                // ignore
            }
        }
    }
}

class RunTest {
public static void main(String[] args) {
// create ThreadTest thread 
ThreadTest tt = new ThreadTest();
tt.start();         // start thread
}
}
