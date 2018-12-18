import java.net.*;
import java.io.IOException;

// given host name, communicates with host via the daytime port

public class TestdateHandler extends URLStreamHandler {
    protected URLConnection openConnection(URL u) throws IOException {
        return new DateURLConnection(u);
    }
}
