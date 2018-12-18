import java.net.*;

class Main {
// START equals
public static boolean localHostP(String hostname) {
    try {
        InetAddress target = InetAddress.getByName(hostname);
	return (target.equals(InetAddress.getLocalHost()));
    } catch (UnknownHostException e) {
	return (false);
    }
}
// END equals


    public static void main(String[] args) {
	System.out.println("host test" + localHostP("ws07"));
	System.out.println("host test" + localHostP("localhost"));

	String target = "ws07";
// START getaddr
try {
    byte [] addr = InetAddress.getLocalHost().getAddress();
    System.out.println("My address: " + 
	(addr[0]&0xff) + "." + (addr[1]&0xff) + "." + 
	(addr[2]&0xff) + "." + (addr[3]&0xff));
    System.out.println("Try again the easy way: " +
	InetAddress.getLocalHost().getHostAddress());	
} catch (UnknownHostException e) {
    e.printStackTrace();
}
// END getaddr
// START getall	
try {
    InetAddress[] alladdrs = InetAddress.getAllByName(target);
    for (int i = 0; i < alladdrs.length; i++)
	System.out.println(target + "[" + i + "] = " + alladdrs[i]);
} catch (UnknownHostException e) {
    System.err.println("Host not found: " + e);
}
// END getall
// START gethost
try {
    InetAddress somehost = InetAddress.getByName(target);
    String somehostName = somehost.getHostName();
    if (target.equals(somehostName))
	System.out.println("same name");
    else
	System.out.println("target is different from host name");
} catch (UnknownHostException e) {
    System.err.println("Host not found: " + e);
}
// END gethost
// START hashCode
{
    int tabsize = 13;
    int[] hits = new int[tabsize];
try {
    InetAddress addr = InetAddress.getByName(target);
    int hashval = addr.hashCode();      // generate hash code
    ++hits[Math.abs(hashval%tabsize)];  // count hits
} catch (UnknownHostException e) {
    System.err.println("Host not found: " + e);
}
// END hash
// START tostr
try {
    InetAddress myAddr = InetAddress.getLocalHost();
    System.out.println(myAddr.toString()); // hostname/a.b.c.d
} catch (UnknownHostException e) {
    System.err.println("Host not found: " + e);
}
// END
}
}
}
