import java.net.*;
import java.util.Hashtable;
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

// Sample factory 
class SampleFactory implements ContentHandlerFactory {
    Hashtable handlers = new Hashtable();
    static private ContentHandler defaultHandler = new SimpleStringHandler();

    // Construct class name for content handler for contentType
    // "majorType/minorTYpe" -> majorType.minorType
    private String mapContentTypeToClassName(String contentType) {
        int len = contentType.length();
        char className[] = new char[len];
        contentType.getChars(0, len, className, 0);
	for (int j = 0; j < len; j++) {
            char c = className[j];
	    // turn '/' to '.'; nonletter and nondigits to '_'
            if (c == '/') {
                className[j] = '.';
            } else if (!Character.isLetterOrDigit(c)) {
                className[j] = '_';
	    }
        }
        return (new String(className));
    }

    public ContentHandler createContentHandler(String contentType) {
        ContentHandler handler = null;
        if (contentType == null)
            return defaultHandler;        // no type specified

        // Check cache first
        handler = (ContentHandler)handlers.get(contentType);
        if (handler != null)
            return handler;

        // Get class name from content
        String className = mapContentTypeToClassName(contentType);
	try {
	    handler = (ContentHandler)Class.forName(className).newInstance();
	} catch(Exception e) {
	    // cannot get handler, just use default
	    handler = defaultHandler;
	}

        // Add newly found handler to cache
        handlers.put(contentType, handler);
        return handler;
    }
}

class Main {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.err.println("Usage: java Main <URL>");
            System.exit(1);
        }

        // Configure system to use our own factory
        URLConnection.setContentHandlerFactory(new SampleFactory());
        try {
            URL url = new URL(args[0]);
        
            Object pkgs_html = url.getContent();
            if (pkgs_html != null) {
                System.out.println("class: " + pkgs_html.getClass());
                System.out.println("obj: " + pkgs_html);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    };
}
