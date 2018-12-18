import java.awt.*;
import java.awt.event.*;

class Main implements Runnable {
    public static void main(String args[] ) {
        try {
            new ThreadGViewer();        // Create the thread viewer

            // periodically create a thread group just to 
            // keep things interesting
            for (int i=0; ; i++) {
                // Create a new thread group.
                ThreadGroup grp = new ThreadGroup("ThreadGroup-"+i);

                // And create a new thread in the thread group.
                (new Thread(grp, new Main())).start();

                Thread.sleep((int)Math.floor(Math.random()*5000));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            while (true) {
                // Check if this thread has been interrupted.
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
                Thread.sleep(500);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ThreadGViewer extends Frame implements Runnable, ActionListener {
    Thread timerThread;

    // List of all thread groups in the system.
    ThreadGroup[] groups;

    // List component containing all the thread groups in the system.
    List threadList = new List();

    ThreadGViewer() {
        super("ThreadGroup Example");
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

    public void actionPerformed(ActionEvent evt) {
        String cmd = evt.getActionCommand();
         if ("Pause".equals(cmd)) {
            timerThread.suspend();
            ((Button)evt.getSource()).setLabel("Resume");
        } else if ("Resume".equals(cmd)) {
            timerThread.resume();
            ((Button)evt.getSource()).setLabel("Pause");
        } else if (evt.getSource() == threadList) {
            ThreadGroup grp = groups[threadList.getSelectedIndex()];
            try {
                grp.checkAccess();

                if (grp.isDestroyed()) {
                    System.out.println(grp + " already destroyed");
                    return;
                }

                // Interrupt & then wait until all threads have stopped.
                Thread[] threads = new Thread[grp.activeCount()];
                int count = grp.enumerate(threads, false);
                for (int i=0; i<count; i++) {
                    threads[i].interrupt();
                    threads[i].join();
                }

                // Now destroy the group.
                grp.destroy();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                System.out.println("No permission to stop thread " +
                    grp.getName());
            }
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
        ThreadGroup curGrp = Thread.currentThread().getThreadGroup();
        ThreadGroup[] grps = new ThreadGroup[curGrp.activeGroupCount()];
        int count = curGrp.enumerate(grps, true);

        groups = new ThreadGroup[count];
        threadList.removeAll();
        int j = 0;
        for (int i=0; i<count; i++) {
            if (Thread.currentThread().getThreadGroup().parentOf(grps[i])) {
                ThreadGroup grp = grps[i];
    
                threadList.addItem(grp.getName() 
                    + "  P" + grp.getMaxPriority() 
                    + "  " + (grp.isDaemon() ? "Daemon" : ""));
                groups[j++] = grp;
            }
        }
    }
}
