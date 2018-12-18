class Main implements Runnable {
    public static void main(String args[]) {
        MainThreadGroup mtg = new MainThreadGroup("MainThreadGroup");
        Thread thread = new Thread(mtg, new Main());

        thread.start();

        // Wait 1 second ... 
        try {
            Thread.sleep(1000);

            // And then stop the thread.
            thread.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class MainThreadGroup extends ThreadGroup {
    MainThreadGroup(String name) {
        super(name);
    }

    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("UNCAUGHT EXCEPTION by " + t.getName());
        e.printStackTrace();
    }
}
