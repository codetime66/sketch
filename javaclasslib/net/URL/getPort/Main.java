import java.net.*;

class Main {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://java.sun.com");
            System.out.println(url.getPort());    // -1

            url = new URL("http://java.sun.com:123");
            System.out.println(url.getPort());    // 123

            url = new URL("ftp://java.sun.com:456");
            System.out.println(url.getPort());    // 456
        } catch (MalformedURLException e) {
            e.printStackTrace();    
        }
    }
}
