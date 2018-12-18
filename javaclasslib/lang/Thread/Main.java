import java.awt.*;
import java.awt.event.*;

class Main implements Runnable {
    public static void main(String args[] ) {
        try {
            new ThreadViewer();

            // periodically create a thread just to keep things interesting
            while (true) {
                Thread.sleep((int)Math.floor(Math.random()*5000));
                (new Thread(new Main())).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        } catch (ThreadDeath e) {
            System.out.println(Thread.currentThread().getName() +
                " has been stopped!");
        }
    }
}

class ThreadViewer extends Frame implements Runnable, ActionListener {
    Thread timerThread;

    // List of all threads in the system.
    Thread[] threads;

    // List component containing all the threads in the system.
    List threadList = new List();

    ThreadViewer() {
        super("Thread Example");
        add(threadList, BorderLayout.CENTER);
        Button b;
        add(b = new Button("Pause"), BorderLayout.NORTH);
        b.addActionListener(this);
        threadList.addActionListener(this);

        setSize(300, 300);
        show();
        timerThread = new Thread(this);
        timerThread.start();
    }

    public synchronized void actionPerformed(ActionEvent evt) {
        String cmd = evt.getActionCommand();
        if (evt.getSource() == threadList) {
            Thread t = threads[threadList.getSelectedIndex()];
            try {
                t.checkAccess();
                t.stop();
            } catch (SecurityException e) {
                System.out.println("No permission to stop thread " + 
                    t.getName());
            }
        } else if ("Pause".equals(cmd)) {
            timerThread.suspend();
            ((Button)evt.getSource()).setLabel("Resume");
        } else if ("Resume".equals(cmd)) {
            timerThread.resume();
            ((Button)evt.getSource()).setLabel("Pause");
        }
    }

    public void run() {
        try {
            while (timerThread == Thread.currentThread()) {
                updateList();
                Thread.sleep(5000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    synchronized void updateList() {
        // Find the root thread group
        ThreadGroup rootGrp = Thread.currentThread().getThreadGroup();

        while (rootGrp.getParent() != null) {
            rootGrp = rootGrp.getParent();
        }

        threads = new Thread[rootGrp.activeCount()];
        int count = rootGrp.enumerate(threads, true);

        threadList.removeAll();
        for (int i=0; i<count; i++) {
            Thread t = threads[i];

            threadList.addItem(t.getName() 
                + "  P" + t.getPriority() 
                + "  G(" + t.getThreadGroup().getName() + ")" 
                + "  [" + t.countStackFrames() + "]"
                + "  " + (t.isDaemon() ? "Daemon" : "") 
                + "  " + (t.isInterrupted() ? "" : "Interrupted")
                + "  " + (t.isAlive() ? "" : "NotAlive"));
        }
    }
}
