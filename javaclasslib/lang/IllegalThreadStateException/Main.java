import java.util.Date;

class T extends Thread {
    public void run() {
        System.out.println(new Date());
    }
}
class Main {
    public static void main(String[] args) {
        T th = new T();
        th.start();
        th.start(); // will raise IllegalThreadStateException
    }
}
