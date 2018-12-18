
import java.io.IOException;
import java.net.*;


class Main {
    public static void main(String[] args) {
	URL.setURLStreamHandlerFactory(new TestFactory());

	try {
	    URL u = new URL("date://localhost");
	    Object content = u.getContent();

System.err.println("Content is : " + content);

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
