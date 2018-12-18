import java.awt.*;
import java.io.*;
import java.util.*;
import java.text.*;

class Main extends Frame {
    Main(Vector lines) {
        super("getLineInstance Example");
        ScrollPane sp = new ScrollPane();
        TextCanvas cv = new TextCanvas(lines, sp);

        // Setup the components and show the frame.
        sp.add(cv);
        add(sp, BorderLayout.CENTER);
        setSize(400, 400);
        show();
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Main <filename>");
            System.exit(1);
        }
        try {
            // Read in the entire contents of the file.
            // Each line is placed into 'v'.
            BufferedReader rd = new BufferedReader(new FileReader(args[0]));
            Vector v = new Vector();
            String line;

            while ((line = rd.readLine()) != null) {
                v.addElement(line);
            }
            rd.close();
            new Main(v);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class TextCanvas extends Component {
    BreakIterator bi = BreakIterator.getLineInstance();
    Vector segments = new Vector();
    Font f = new Font("Monospaced", Font.PLAIN, 14);
    FontMetrics fm;
    ScrollPane scrollpane;

    TextCanvas(Vector lines, ScrollPane sp) {
        scrollpane = sp;
        fm = getFontMetrics(f);

        for (int i=0; i<lines.size(); i++) {
            int start = 0;
            String line = (String)lines.elementAt(i);
            bi.setText(line);

            while (bi.next() != bi.DONE) {
                segments.addElement(line.substring(start, bi.current()));
                start = bi.current();
            }
            segments.addElement("\n");
        }
    }

    // Determines the dimensions of the component given
    // the width of the scrollpane viewport.
    public Dimension getPreferredSize() {
        int x = 0;
        int y = 0;
        int w = scrollpane.getViewportSize().width;

        for (int i=0; i<segments.size(); i++) {
            String word = (String)segments.elementAt(i);

            if (word.equals("\n")) {
                // Start a new line.
                x = 0;
                y++;
            } else {
                int sw = fm.stringWidth(word);
                
                // Need to break?
                if (x + sw > w) {
                    x = 0;
                    y++;
                }
                x += sw;
            }
        }
        return new Dimension(w, y * fm.getHeight());
    }

    public void paint(Graphics g) {
        int x = 0;
        int y = fm.getAscent();
        int w = getSize().width;

        g.setFont(f);
        for (int i=0; i<segments.size(); i++) {
            int start = 0;
            String word = (String)segments.elementAt(i);

            if (word.equals("\n")) {
                // Start a new line.
                x = 0;
                y += fm.getHeight();
            } else {
                int sw = fm.stringWidth(word);
                
                // Need to break?
                if (x + sw > w) {
                    x = 0;
                    y += fm.getHeight();
                }
                g.drawString(word, x, y);
    
                x += sw;
            }
        }
    }
}
