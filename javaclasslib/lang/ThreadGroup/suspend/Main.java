import java.awt.*;
import java.awt.event.*;

class Main extends Frame implements ActionListener {
    Label loLabel = new Label();
    Label hiLabel = new Label();
    ThreadGroup hiGroup = new ThreadGroup("High");
    ThreadGroup loGroup = new ThreadGroup("Low");
    
    Main() {
        super("suspend Example");

        // Limit the priority in the lo priority thread group.
        loGroup.setMaxPriority(Thread.MIN_PRIORITY);

        // Add a few threads.
        (new Worker(hiGroup, hiLabel)).start();
        (new Worker(hiGroup, hiLabel)).start();
        (new Worker(loGroup, loLabel)).start();
        (new Worker(loGroup, loLabel)).start();

        add(hiLabel, BorderLayout.NORTH);
        add(loLabel, BorderLayout.SOUTH);
        Button b;
        add(b = new Button("suspend"), BorderLayout.CENTER);
        b.addActionListener(this);

        pack();
        show();
    }

    public synchronized void actionPerformed(ActionEvent evt) {
        String cmd = evt.getActionCommand();
        if ("suspend".equals(cmd)) {
            hiGroup.suspend();
            ((Button)evt.getSource()).setLabel("resume");
        } else if ("resume".equals(cmd)) {
            hiGroup.resume();
            ((Button)evt.getSource()).setLabel("suspend");
        }
    }

    static public void main(String[] args) {
        new Main();
    }
}

class Worker extends Thread {
    Label label;

    Worker(ThreadGroup group, Label label) {
        super(group, "Worker");
        this.label = label;
    }

    public void run() {
        try {
            for (int i=0; ; i++) {
                if (i % 10 == 0) {
                    label.setText(""+i);
                }
                Thread.sleep(16);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
