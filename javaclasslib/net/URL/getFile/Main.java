import java.net.*;
import java.io.*;

class Main {
    public static void main(String[] args) {
        try {
            URL u = new URL("http://java.sun.com/new.html#_top_");
            String protocol = u.getProtocol(); // "http"
            String host = u.getHost();  // "java.sun.com"
            int port = u.getPort();     // -1 (unspecified)
            String file = u.getFile();  // new.html
            String frag = u.getRef();   // _top_
    
            System.out.println(protocol + "|" + host + "|" + port + "|" 
                + file + "|" + frag);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
