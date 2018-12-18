import java.io.IOException;
import java.net.*;

class Main {
    public static void main(String[] args) {
        URL.setURLStreamHandlerFactory(new TestFactory());
        String host = "localhost";
        if (args.length > 0)
            host = args[0];
        try {
            URL u = new URL("date://" + host);
            Object content = u.getContent();

            if (content != null) {
                System.out.println("class: " + content.getClass());
                System.out.println("obj: " + content);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
