class Main {
    public static void main(String args[]) {
        for (int i=0; i<Timer.NUM_TESTS; i++) {
            Timer timer = new Timer(i);
            long time = System.currentTimeMillis();
            double usPerOp;

            try {
                timer.start();
                Thread.sleep(5000);    // Wait 5 seconds.

                time = System.currentTimeMillis() - time;
                usPerOp = time * 1000.0 / timer.count;
                System.out.println(timer.label + ": " + usPerOp + " us/op");

                timer.stop();          // Stop the thread.

                Thread.sleep(1000);    // Wait a second for it to terminate.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Timer extends Thread {
    static int NUM_TESTS = 6;
    int count;
    int testType;
    String label;

    Timer(int testType) {
        this.testType = testType;
    }

    public void run() {
        switch (testType) {
          case 0:
            label = "i++";
            while (true) {
                count++;
            }
          case 1:
            label = "Non-synchronized method call";
            while (true) {
                nonSynchronizedMethod();
                count++;
            }
          case 2:
            label = "Synchronized method call";
            while (true) {
                synchronizedMethod();
                count++;
            }
          case 3:
            label = "Math.random()";
            while (true) {
                Math.random();
                count++;
            }
          case 4:
            label = "Thread.interrupted";
            while (true) {
                interrupted();
                count++;
            }
          case 5:
            label = "Thread.sleep(60)";
            while (true) {
                try {
                    Thread.sleep(60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count++;
            }
        }
    }

    synchronized void synchronizedMethod() {
    }

    void nonSynchronizedMethod() {
    }
}
