import java.net.*;

class Main {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://java.sun.com");
            System.out.println(url.getProtocol());    // http

            url = new URL("ftp://java.sun.com");
            System.out.println(url.getProtocol());    // ftp

            // The following causes a MalformedURLException
            // since there is no protocol handler for foobar.
            url = new URL("foobar://java.sun.com");
        } catch (MalformedURLException e) {
            e.printStackTrace();    
        }
    }
}
