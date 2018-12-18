import java.net.*;
import java.io.*;

// Content handler that expects content to be Text String
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

// Sample factory that always return SimpleStringHandler
class SampleFactory implements ContentHandlerFactory {
    static private ContentHandler defaultHandler = new SimpleStringHandler();
    public ContentHandler createContentHandler(String mimeType) {
        // ignore mimeType
        return defaultHandler;
    }
}

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("java Main <http url>");
            System.exit(-1);
        }
        // Configure system to use our own factory
        URLConnection.setContentHandlerFactory(new SampleFactory());

        try {
            // Directly use implementation to get 
            HttpImpl conn = new HttpImpl(new URL(args[0]));

            // Retrieve contents
            Object obj = conn.getContent();

            // Display what we've found
            System.out.println(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
