import java.io.*;
import java.net.*;
import java.util.Hashtable;
import java.util.Vector;

public class HttpImpl extends HttpURLConnection {
    Socket sock = null;

    public HttpImpl(URL url) {
        super(url);
    }

    // Establishes connection to HTTP server and sends request
    // Implementation of abstract method defined in URLConnection
    public void connect() throws IOException {
        if (connected) {  // connected is defined in URLConnection
            return;
        }
        InetAddress dst = InetAddress.getByName(getURL().getHost());
        int port;
        if ((port = getURL().getPort()) == -1)
            port = 80;        // default port number for HTTP
        sock = new Socket(dst, port);
        OutputStream out = sock.getOutputStream();

        send(out, getRequestMethod() + " " + url.toString() + 
             " HTTP/1.1\r\n");
        send(out, "\r\n");
        connected  = true;
    }

    // Disconnects from HTTP server.
    // Implementation of abstract method defined in HttpURLConnection
    public void disconnect() {
        if (sock != null) {
            try {
                sock.close();
            } catch (IOException e) {
            }
            sock = null;
        }
        connected = false;
    }

    // Implementation of abstract method defined in HttpURLConnection
    public boolean usingProxy() {
        return false; // this simple impl does not use proxy
    }

    // Override default provided in URLConnection
    public InputStream getInputStream() throws IOException {
        if (!connected) {
            connect();
        }
        return sock.getInputStream();
    }

    // Override default provided in URLConnection
    public OutputStream getOutputStream() throws IOException {
        if (!connected) {
            connect();
        }
        return sock.getOutputStream();
    }

    // Override default provided in URLConnection
    public String getHeaderField(int n) {
        getHeaders();
        if (n < headers.size()) {
            return getField((String)headers.elementAt(n));
        }
        return null;
    }

    // Override default provided in URLConnection
    public String getHeaderField(String key) {
        getHeaders();
        return (String)keys.get(key.toLowerCase());
    }

    // Override default provided in URLConnection
    public String getHeaderFieldKey(int n) {
        getHeaders();
        if (n < headers.size()) {
            return getKey((String)headers.elementAt(n));
        }
        return null;
    }

    // Helper routine to send a string to an output stream
    static void send(OutputStream out, String s) throws IOException {
        for (int i=0; i<s.length(); i++) {
            out.write((byte)s.charAt(i));
        }
        out.flush();
    }

    // Helper routine to read a newline-terminated string from input stream
    static String recv(InputStream in) throws IOException {
        String result = "";
        int c = in.read();

        while (c >= 0 && c != '\n') {
            if (c != '\r') {
                result += (char)c;
            }
            c = in.read();
        }
        return result;
    }

    // Helper routines for parsing header field
    private static final char keySeparator = ':';
    static String getKey(String str) {
        if (str == null)
            return null;
        int ind = str.indexOf(keySeparator);
        if (ind >= 0)
            return str.substring(0, ind).toLowerCase();
        return null;
    }

    static String getField(String str) {
        if (str == null)
            return null;
        int ind = str.indexOf(keySeparator);
        if (ind >= 0)
            return str.substring(ind+1).trim();
        else
            return str;
    }

    // Helper routine that reads header from HTTP connection
    Hashtable keys = new Hashtable();
    Vector headers = new Vector();
    boolean gotten = false;
    void getHeaders() {
        if (gotten)
            return;
        gotten = true;
        try {
            connect();
            InputStream in = sock.getInputStream();
            while (true) {
                String header = recv(in);
                if (header.length() == 0)
                    break;
                headers.addElement(header);
                String key = getKey(header);
                if (key != null) {
                    keys.put(key, getField(header));
                 }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
