import java.net.*;
import java.io.IOException;
import java.io.OutputStream;

class Main {
    public static void main(String[] args) {
        try {
            URL u = new URL(
                "file://localhost/export/home/java/api/packages.html");
            URLConnection conn = u.openConnection();

            OutputStream out = conn.getOutputStream();
        } catch (UnknownServiceException e) {
            System.err.println("Cannot get output stream: "+ e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
