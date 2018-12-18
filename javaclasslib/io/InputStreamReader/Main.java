import java.io.*;
import java.awt.*;

class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println(
                "Usage: java Main <input>");
            System.exit(-1);
        }
        try {
            Reader in = new BufferedReader(
                new InputStreamReader(new FileInputStream(args[0]), "UTF8"));

            StringBuffer buf = new StringBuffer();
            int ch;
            while ((ch = in.read()) > -1) {
                buf.append((char)ch);
            }
            in.close();
            new StringWin(buf.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class StringWin extends Frame {
    FontMetrics fontM;
    String str;
    
    StringWin(String str) {
        this.str = str;

        Font font = new Font("Monospaced", Font.BOLD, 72);
        fontM = getFontMetrics(font);
        setFont(font);

        // Calculate total width.
        int size = 0;
        for (int i = 0; i<str.length(); i++) {
            size += fontM.charWidth(str.charAt(i));
        }
        size += 2;

        // Set bounds and show.
        setSize(size, fontM.getHeight()+20);
        setLocation(getSize().width/2, getSize().height/2);
        show();
    }

    public void paint(Graphics g) {
        Insets insets = getInsets();
        int x = insets.left, y = insets.top;
        g.drawString(str, x, y+fontM.getAscent());
    }
}
