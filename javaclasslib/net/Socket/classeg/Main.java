import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.util.*;

class Main extends Frame implements ActionListener, Runnable {
    static final int DELAY_MS = 10 * 60 * 1000;    // 10 minutes.

    // The number of new messages in the POP3 server.
    int numMessages;

    // Socket parameters.
    String hostname;
    String user;
    String password;
    int port;

    // Components.
    Label message = new Label("", Label.CENTER);
    Button okBtn = new Button("OK");

    Main(String user, String hostname, String password, int port) {
        super("New Mail Notifier");
        this.user = user;
        this.hostname = hostname;
        this.password = password;
        this.port = port;

        // Create user interface.
        add(message, BorderLayout.CENTER);
        add(okBtn, BorderLayout.SOUTH);
        okBtn.addActionListener(this);
        setSize(250, 100);

        (new Thread(this)).start();
    }

    public void actionPerformed(ActionEvent evt) {
        setVisible(false);
    }

    public void run() {
        while (true) {
            Socket socket = null;

            try {
                socket = new Socket(hostname, port);
                BufferedInputStream is = new BufferedInputStream(
                    socket.getInputStream());
                BufferedOutputStream os = new BufferedOutputStream(
                    socket.getOutputStream());
                String line;

                // Skip welcome message.
                recv(is);

                send(os, "USER " + user + "\r\n");
                if (error(line = recv(is))) {
                    continue;
                }

                send(os, "PASS " + password + "\r\n");
                if (error(line = recv(is))) {
                    continue;
                }

                send(os, "STAT\r\n");
                if (error(line = recv(is))) {
                    continue;
                }

                // Retrieve number of messages from STAT command.
                StringTokenizer st = new StringTokenizer(line);
                st.nextToken();    // skip +OK
                int m = Integer.parseInt(st.nextToken());
                if (m != numMessages) {
                    if (m > numMessages) {
                        message.setText("There are "+m+" new messages.");
                        show();
                        toFront();
                    }
                    numMessages = m;
                }

                send(os, "QUIT");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            // Sleep
            try {
                Thread.sleep(DELAY_MS);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Display an error message if the reply does not start with a '+'.
    boolean error(String msg) {
        if (msg.charAt(0) != '+') {
            message.setText("ERROR: "+msg);
            show();
            return true;
        }
        return false;
    }

    // Sends a string to the POP3 server.
    void send(OutputStream os, String s) throws IOException {
        for (int i=0; i<s.length(); i++) {
            os.write((byte)s.charAt(i));
        }
        os.flush();
    }

    // Receives a reply from the POP3 server.
    String recv(InputStream is) throws IOException {
        String result = "";
        int c = is.read();

        while (c >= 0 && c != '\n') {
            if (c != '\r') {
                result += (char)c;
            }
            c = is.read();
        }
        return result;
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            System.err.println("Usage: java Main <user> <host> "
                + "<password> [port]");
            return;
        }

        if (args.length > 3) {
            new Main(args[0], args[1], args[2], Integer.parseInt(args[3]));
        } else {
            new Main(args[0], args[1], args[2], 110);
        }
    }
}
