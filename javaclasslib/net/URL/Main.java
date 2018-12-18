import java.net.*;
import java.io.*;

class Main {
    public static void main (String[] args) {
// START const
try {
    String protocol = "file";
    String host = "localhost";
    int port = 0;
    String file = "/app/java1.1/index.html";
	    URL u1 = new URL(protocol, host, port, file);
    URL u2 = new URL(protocol, host, file);
    URL u3 = new URL(protocol + "://" + host + "/");
    URL u4 = new URL(u3, file + "#_top_");

    Object content = u4.getContent();
    if (content != null) {
	System.out.println("class: " + content.getClass());
	System.out.println("obj: " + content);
    }

    InputStream in = u4.openStream();
    if (in != null) {
	for(int c = in.read(); c > 0; c = in.read()) {
	    System.out.print((char)c);
	}
    }
} catch (MalformedURLException e) {
    e.printStackTrace();
} catch (IOException e) {
    e.printStackTrace();
}
// END const

System.out.println("____________________ access test ____________________");
// START access
try {
    URL u = new URL("http://java.sun.com/new.html#_top_");
    String protocol = u.getProtocol(); // "http"
    String host = u.getHost(); 	// "java.sun.com"
    int port = u.getPort();	// -1 (unspecified)
    String file = u.getFile();	// new.html
    String frag = u.getRef();	// _top_
    
    System.out.println(protocol + host + port + file + frag);
} catch (MalformedURLException e) {
    e.printStackTrace();
}
// END access
System.out.println("____________________ samefile test ____________________");
// START samefile
try {
    URL u1 = new URL("http://java.sun.com:80/new.html");
    URL u2 = new URL("http://java.sun.com/new.html");
    URL u3 = new URL("http://java.sun.com/new.html#_top_");
	    // u1 and u2 are not the same because of 'port'
    System.out.println(u1 + (u1.sameFile(u2)? " is " : " is not ") +
		       "the same as " + u2);
    // u2 and u3 are the same because fragment is ignored
    System.out.println(u2 + (u2.sameFile(u3)? " is " : " is not ") +
		       "the same as " + u3);
    
} catch (MalformedURLException e) {
    e.printStackTrace();
}
// END samefile
System.out.println("____________________ equals test ____________________");
// START equals
try {
    URL u1 = new URL("http://java.sun.com:80/new.html");
    URL u2 = new URL("http://java.sun.com/new.html");
    URL u3 = new URL("http://java.sun.com/new.html#_top_");
	    // u1 and u2 are not the same because of 'port'
    System.out.println(u1 + (u1.equals(u2)? " is " : " is not ") +
		       "the same as " + u2);
    // u2 and u3 are not the same because fragments are different
    System.out.println(u3 + (u3.equals(u2)? " is " : " is not ") +
		       "the same as " + u2);
} catch (MalformedURLException e) {
    e.printStackTrace();
}
// END equals
System.out.println("____________________ hashh test ____________________");
// START hash
try {
    int[] hits = new int[1023];
    URL u = new URL("http://java.sun.com/");
    int hashval = u.hashCode();
    ++hits[Math.abs(hashval%hits.length)];  // count hits
} catch (MalformedURLException e) {
    e.printStackTrace();
}
// END hash
System.out.println("____________________ print test ____________________");
// START print
try {
    URL u = new URL("http", "java.sun.com", 80, "new.html");
    if (u.toString().equals(u.toExternalForm()))
	System.out.println("external form is same as string form");
    else
	System.out.println("external form is different");
} catch (MalformedURLException e) {
    e.printStackTrace();
}
// END print
System.out.println("____________________ openconn test ____________________");
// START openconn
try {
    URL u = new URL("file://localhost/app/java1.1/index.html");
    System.out.println("host of URL is " + u.getHost());
    URLConnection uconn = u.openConnection();
    Object content = uconn.getContent();
    if (content != null) {
	System.out.println("class: " + content.getClass());
	System.out.println("obj: " + content);
    }
} catch (MalformedURLException e) {
    e.printStackTrace();
} catch (IOException e) {
    e.printStackTrace();
}
// END openconn
    }
}
