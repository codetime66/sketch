import java.awt.*;
class Main {
    public static void main(String args[] ) {
        ThreadGroup grp = Thread.currentThread().getThreadGroup();

        for (int i=0; i<5; i++) {
            // Create a new thread group.
            ThreadGroup g = new ThreadGroup(grp, "group-"+i);

            grp = g;
        }
        Thread.currentThread().getThreadGroup().list();
    }
}
