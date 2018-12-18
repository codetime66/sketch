import java.util.*;

class Main implements TickListener {
    Main() {
        Timer timer = new Timer(3000);
        try {
            timer.addTickListener(this);
        } catch (TooManyListenersException e) {
            e.printStackTrace();
        }
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
    TickListener listener;

    Timer(int periodMillis) {
        period = periodMillis;
        start();
    }

    // Listener registration methods.
    void addTickListener(TickListener listener) 
        throws TooManyListenersException {
        if (listener != null) {
            throw new TooManyListenersException();
        }
    }
    void removeTickListener(TickListener listener) {
        listener = null;
    }

    public void run() {
        while (true) {
            try {
                // This avoids a race condition 
                TickListener l = listener;

                // Deliver a TickEvent to the listeners.
                if (l != null) {
                    l.ticked(new TickEvent(this));
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
