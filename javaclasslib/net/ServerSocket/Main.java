import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;

class Main extends Frame implements ActionListener, Runnable {
    TtyCanvas tty = new TtyCanvas();
    TextField input = new TextField();
    DataInputStream in;
    DataOutputStream out;

    Main(InputStream in, OutputStream out) {
        this.in = new DataInputStream(in);
        this.out = new DataOutputStream(out);

        // Add components.
        add(tty, BorderLayout.CENTER);
        add(input, BorderLayout.SOUTH);
        input.addActionListener(this);

        // Display frame.
        setSize(400, 300);
        show();

        // Start the network reading thread.
        new Thread(this).start();
    }
    
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == input) {
            try {
                // Send the line to the other program and then display it.
                out.writeUTF(input.getText());
                tty.append(input.getText(), Color.blue);
            } catch (Exception e) {
                tty.append("Connection broken", Color.red);
            }
            input.setText(null);
        } 
    }

    public void run() {
        try {
            while (true) {
                // Wait for a line from the other program.
                String line = in.readUTF();
                tty.append(line, Color.black);
            }
        } catch (Exception e) {
            tty.append("Connection broken", Color.red);
        }
    }

    public static void main(String[] args) {
        Socket socket = null;

        try {
            if (args.length == 1) {
                // Server
                ServerSocket ssocket = new ServerSocket(
                    Integer.parseInt(args[0]));
                System.out.println("port: " + ssocket.getLocalPort());
                socket = ssocket.accept();
            } else if (args.length == 2) {
                // Client
                socket = new Socket(args[0], Integer.parseInt(args[1]));
            } else {
                System.err.println("server: java Main <port>");
                System.err.println("client: java Main <hostname> <port>");
                System.exit(1);
            }

            new Main(socket.getInputStream(), socket.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}

// This class implements a simple text display that can
// display lines with different colors.
class TtyCanvas extends Component {
    // Vector of current lines.
    Vector lines = new Vector();

    // Vector of new lines.
    Vector newLines = new Vector();
 
    synchronized void append(String s, Color c) {
        newLines.addElement(new TtyLine(s, c));
        repaint();
    }

    public void paint(Graphics g) {
        synchronized (this) {
            // Transfer new lines into lines.
            while (newLines.size() > 0) {
                lines.addElement(newLines.elementAt(0));
                newLines.removeElementAt(0);
            }

            // Keep at most the last 50 lines.
            while (lines.size() > 50) {
                lines.removeElementAt(0);
            }
        }

        FontMetrics fm = g.getFontMetrics();
        int margin = fm.getHeight()/2;
        int w = getSize().width;
        int y = getSize().height-fm.getHeight()-margin;
        
        // Paint all the lines bottom up.
        for (int i=lines.size()-1; i>=0; i--) {
            TtyLine tl = (TtyLine)lines.elementAt(i);
            g.setColor(tl.c);
            g.drawString(tl.s, margin, y+fm.getAscent());
            y -= fm.getHeight();
        }
    }
}

// Convenient class to hold a string and its color.
class TtyLine {
    String s;
    Color c;
    TtyLine(String s, Color c) {
        this.s = s;
        this.c = c;
    }
}
