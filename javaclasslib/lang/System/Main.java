/*
// START array
// Implementation of String.getChars().
public void getChars(int srcBegin, int srcEnd, char dst[], int dstBegin) {
    System.arraycopy(value, offset + srcBegin, dst, dstBegin, 
        srcEnd - srcBegin);
}
// END array
*/

import java.util.Date;
import java.util.Properties;
import java.util.Enumeration;
import java.io.*;

class Main {
// START err
public static void usage() {
    System.err.println("Usage: testprog <username> <age>");
    System.exit(-1);
}
// END err

// START in
// reads a line from standard input
public static String getLine() {
    StringBuffer buf = new StringBuffer(80);
    int c;
    try {
        while ((c = System.in.read()) != -1) {
            char ch = (char) c;
            if (ch == '\n')
                break;
            buf.append(ch);
        }
    } catch (IOException e) {
        System.err.println(e);
    }
    return (buf.toString());
}
// END in

public static void setIO() {
    try {
// START setIO
System.setIn(new java.io.FileInputStream("myinputfile"));
System.setOut(new PrintStream(new java.io.FileOutputStream("myoutputfile")));
System.setErr(new PrintStream(new java.io.FileOutputStream("myerrinputfile")));

System.out.println("Hello there file");		// using new out
System.err.println("Hello there err file");     // using new err
// END setIO

} catch (java.io.IOException e) {
    e.printStackTrace();
}
}

public static void main(String[] args) {
// START date
// Create a Date object using today's date
Date today = new Date(System.currentTimeMillis());
System.out.println("Today: " + today.toString());
// END date
{
// START getprops
Properties props = System.getProperties(); // get list of properties
// Print properties using Enumeration
for (Enumeration enum = props.propertyNames(); enum.hasMoreElements();) {
    String key = (String)enum.nextElement();
    System.out.println(key + " = " + (String)(props.get(key)));
}
// END getprops
setIO();
}
{
// START getprop
// get user's home directory
String homeDir = System.getProperty("user.home");
// If 'outDir' not found, use 'homeDir' as default
String outDir = System.getProperty("testdir", homeDir);
// END getprop
System.out.println("homeDir: " + homeDir);
System.out.println("outDir: " + outDir);
}
/*
// START getsec
// Implementation of Thread.checkAccess()
public final void checkAccess() {
    SecurityManager security = System.getSecurityManager();
    if (security != null) {
        security.checkAccess(this);
    }
}
// END getsec
*/
{
String s1 = getLine();
System.out.println("in: " + s1);
}
{
// START setprops
Properties props = System.getProperties();
// Add 'outDir' property
props.put("outDir", "/tmp");
// overwrites System properties with new properties
System.setProperties(props);
// END setprops
System.out.println("outDir: " + System.getProperty("outDir"));
}

usage();
}
}

