class Main {
    public static void main(String args[] ) {
        for (int i=0; i<10; i++) {
            (new Worker(i)).start();
        }
    }
}

class Worker extends Thread {
    int id;
    static int lastRunningWorker;

    Worker(int id) {
        this.id =  id;
    }

    public void run() {
        int i = 0;

        // Here is where the work gets done.
        while (true) {
            synchronized (this) {
                if (id != lastRunningWorker) {
                    System.out.print(id);
                    lastRunningWorker = id;
                }
            }
            if (i++ % 100 == 0) {
                System.out.print("*");
                Thread.yield();
            }
        }
    }
}
