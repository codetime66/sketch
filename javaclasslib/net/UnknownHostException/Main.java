import java.net.InetAddress;
import java.net.UnknownHostException;

class Main {
    public static void main(String[] args) {
        System.out.println("UnknownHostException example");
        try {
            InetAddress someAddr = InetAddress.getByName("NeverFindThis");
        } catch (UnknownHostException e) {
            System.err.println("Cannot resolve: " + e);
        }
    }
}
