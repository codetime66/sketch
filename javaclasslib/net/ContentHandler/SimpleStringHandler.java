import java.net.*;
import java.io.*;

// Content handler that reads input stream and returns a String
class SimpleStringHandler extends ContentHandler {
    public Object getContent(URLConnection conn) throws IOException {
        InputStream input = conn.getInputStream();
        StringBuffer buf = new StringBuffer();
        int c;
        while ((c = input.read()) >= 0) {
            buf.append((char) c);
        }
        input.close();
        return (buf.toString());
    }
}
