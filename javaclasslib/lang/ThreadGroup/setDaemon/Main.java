class Main {
    public static void main(String args[] ) {
        ThreadGroup grp = new ThreadGroup("Group");

        grp.setDaemon(true);
        // Create a few workers
        for (int i=0; i<5; i++) {
            (new Worker(grp)).start();
        }
    }
}

class Worker extends Thread {
    Worker(ThreadGroup grp) {
        super(grp, "Worker");
    }

    public void run() {
        try {
            Thread.sleep((int)Math.floor(Math.random()*5000));
            System.out.println("done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
