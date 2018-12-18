import java.util.Properties;
import java.util.Enumeration;
import java.io.*;

/*
// START defaults
public String getProperty(String key) {
    String val = (String)super.get(key);
	return ((val == null) && (defaults != null)) ? 
		defaults.getProperty(key) : val;
    }
// END defaults
*/

class Main {
    public static void main (String[] args) {
// START load
Properties props = new Properties();  // empty list

Properties sysprops = System.getProperties();
	
try {
    FileOutputStream out = new FileOutputStream("/tmp/props");
    sysprops.save(out, "/* testing */");
    FileInputStream in = new FileInputStream("/tmp/props");
    
    props.load(in);

    System.out.println("Got properties from /tmp/props");
    props.list(System.out);
} catch (IOException e) {
    System.out.println(e);
}
// END load
// START getprop
// Look for property
System.out.println("java.version: " + 
		   props.getProperty("java.version"));

// Look for property with default
System.out.println("notthere: " +
		   props.getProperty("notthere", "default"));
// END getprop

// START propname
// Enumeration properties
for (Enumeration e = props.propertyNames();
     e.hasMoreElements();
     System.out.println("\t" + (String)e.nextElement()));
// END propname

// START list
// Make copy of properties
Properties props_copy = new Properties(props);
props.list(System.out);		// print out for debugging
// END list
    }
}
