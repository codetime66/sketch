import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;

class Main extends Frame implements Runnable, ItemListener {
    Label home = new Label("", Label.CENTER);
    Label away = new Label("", Label.CENTER);
    Choice tzCh = new Choice();

    Main() {
        super("TimeZone example");

        Panel p = new Panel(new GridLayout(0, 2));
        p.add(createHeader("  HOME  "));
        p.add(createHeader("  AWAY  "));
        p.add(home);
        p.add(away);
        add(p, BorderLayout.NORTH);

        String[] ids = TimeZone.getAvailableIDs();
        for (int i=0; i<ids.length; i++) {
            tzCh.add(ids[i]);
        }

        add(tzCh, BorderLayout.SOUTH);
        tzCh.addItemListener(this);

        new Thread(this).start();
        setSize(300, 200);
        show();
    }
    
    String getDate(Date date, TimeZone tz) {
        DateFormat formatter
            = new SimpleDateFormat("MMM dd HH:mm:ss zzz", Locale.US);
        formatter.setTimeZone(tz);
        return formatter.format(date);
    }

    Label createHeader(String s) {
        Label l = new Label(s, Label.CENTER);
        l.setFont(new Font("Monospaced", Font.BOLD, 18));
        l.setBackground(Color.black);
        l.setForeground(Color.white);
        return l;
    }

    TimeZone curTz = TimeZone.getDefault();

    
    public void itemStateChanged(ItemEvent evt) {
        curTz = TimeZone.getTimeZone((String)evt.getItem());
    }

    public void run() {
        try {
            Date date = new Date();
            TimeZone homeTz = TimeZone.getDefault();

            while (true) {
                date.setTime(System.currentTimeMillis());
                home.setText(getDate(date, homeTz));
                away.setText(getDate(date, curTz));
                Thread.sleep(5000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
