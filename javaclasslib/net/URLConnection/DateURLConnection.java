import java.net.*;
import java.io.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;

public class DateURLConnection extends URLConnection {
    DatagramSocket sock = null;
    public DateURLConnection(URL u) {
        super(u);
    }
    public void connect() throws IOException {
        if (connected) {
            return;
        }
        InetAddress dst = InetAddress.getByName(getURL().getHost());
        byte[] outbuf = new byte[1];
        outbuf[0] = '\n';
        int port;
        if ((port = getURL().getPort()) == -1)
            port = 13;        // daytime

        DatagramPacket request = 
            new DatagramPacket(outbuf, outbuf.length, dst, port);
        try {
            sock = new DatagramSocket();
            sock.send(request);
            connected = true;
        } catch (SocketException e) {
            sock = null;
            throw e;
        }
    }
    // Override instead letting content handler be selected
    // based on content-type 
    public Object getContent() throws IOException {
        if (!connected)
            connect();

        byte[] inbuf = new byte[256];  // default size
        DatagramPacket reply = new DatagramPacket(inbuf, inbuf.length);
        sock.receive(reply);
        sock.close();
        sock = null;
        connected = false;
        String dateStr = new String(reply.getData());
        if (dateStr != null) {
            try {
                DateFormat df = DateFormat.getDateInstance(DateFormat.FULL);
                return (df.parse(dateStr));
            } catch (ParseException e) {
                System.err.println("Date string: " + dateStr);
                e.printStackTrace();
            }
        }
        throw new ProtocolException("Not conforming to date protocol");
    }
}
