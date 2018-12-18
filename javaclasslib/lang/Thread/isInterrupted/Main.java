import java.util.Random;

class Main {
    public static void main(String[] args) {
        Thread t;

        if (args.length > 0) {
            t = new Counter();
        } else {
            t = new Sleeper();
        }
                
        t.start();

        Random rand = new Random();
        while (true) {
            int p = Math.abs(rand.nextInt()%5000);
            System.out.println("wake up worker in " + p + "ms");
            try {
                Thread.sleep(p);
            } catch (InterruptedException e) {
                System.out.println("main interrupted");
            }
            t.interrupt();
        }
    }
}

class Sleeper extends Thread {
    public void run() {
        while (true) {
            try {
                Thread.sleep(2000);
                System.out.println("sleeper woke up");
            } catch (InterruptedException e) {
                System.out.println("sleeper interrupted (1): " 
                   + isInterrupted());
                System.out.println("sleeper interrupted");
            }
            System.out.println("sleeper interrupted (2): " + isInterrupted());
        }
    }
}

class Counter extends Thread {
    public void run() {
        while (true) {

            long sum = 0;
            for (int i = 0; i< 1000000 && !isInterrupted(); i++) {
                sum += i;
            }
            System.out.println("Sum: " + sum);

            System.out.println("isInterrupted (0): " + isInterrupted());
            System.out.println("isInterrupted (1): " + isInterrupted());

            System.out.println("interrupted(0): " + interrupted());
            // First call should have cleared it.
            System.out.println("interrupted (1): " + interrupted());
            
        }
    }
}
