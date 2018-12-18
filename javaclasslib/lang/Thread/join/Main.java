class Main {
    public static void main(String args[] ) {
        // Create a slot for each priority level.
        Worker[] workers = 
            new Worker[Thread.MAX_PRIORITY-Thread.MIN_PRIORITY];

        // Create the workers.
        for (int i=0; i<workers.length; i++) {
            workers[i] = new Worker(Thread.MIN_PRIORITY+i);
            workers[i].start();
        }

        // Now wait for them to terminate.
        for (int i=workers.length-1; i >= 0; i--) {
            try {
                workers[i].join();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(workers[i].getName() + ": " +
                workers[i].time + " ms");
        }
    }
}

class Worker extends Thread {
    // Record current time.
    long time = System.currentTimeMillis();

    Worker(int priority) {
        setPriority(priority);
        setName("Worker-"+priority);
    }

    public void run() {
        // Here is where the work gets done.
        String s = "";
        for (int i=0; i<1024; i++) {
            s += i;
        }

        // Record time.
        time = System.currentTimeMillis() - time;
    }
}
