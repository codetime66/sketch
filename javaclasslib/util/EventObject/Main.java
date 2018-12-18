import java.util.*;

class Main implements TickListener {
    Main() {
        Timer timer = new Timer(3000);
        timer.addTickListener(this);
    }

    // Called whenever timer ticks.
    public void ticked(TickEvent evt) {
        System.out.println(evt.getTime());
    }

    public static void main(String[] args) {
        new Main();
    }
}

class Timer extends Thread {
    int period;
    Vector listeners = new Vector();

    Timer(int periodMillis) {
        period = periodMillis;
        start();
    }

    // Listener registration methods.
    void addTickListener(TickListener listener) {
        listeners.addElement(listener);
    }
    void removeTickListener(TickListener listener) {
        listeners.removeElement(listener);
    }

    public void run() {
        while (true) {
            try {
                // This avoids a race condition 
                Vector v = (Vector)listeners.clone();

                // Deliver a TickEvent to all listeners.
                for (int i=0; i<v.size(); i++) {
                    ((TickListener)v.elementAt(i)).ticked(new TickEvent(this));
                }

                Thread.sleep(period);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class TickEvent extends EventObject {
    long time;

    TickEvent(Object source) {
        super(source);
        time = System.currentTimeMillis();
    }

    long getTime() {
        return time;
    }
}

interface TickListener {
    void ticked(TickEvent evt);
}