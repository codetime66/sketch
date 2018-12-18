import java.util.StringTokenizer;
import java.util.Enumeration;
import java.util.NoSuchElementException;

class Main {
public static void main(String[] args) {
{
    // use default delimiters
// START main0
String s = "99 cups of Java";
StringTokenizer parser = new StringTokenizer(s);
// COM
System.out.println("\nInput: " + s);
System.out.println("There are " + parser.countTokens() + 
		   " entries in the path");
// COM
try {
    while(parser.hasMoreTokens()) {
	System.out.println(parser.nextToken());
    }
} catch (NoSuchElementException e) {
    System.out.println(e);
}
// END main0
}
{
    // Example that uses a ";" as a separator
// START main1
String s = "c:\\windows\\command;c:\\dos;c:\\bin;c:\\util";
StringTokenizer parser = new StringTokenizer(s, ";");
// COM
System.out.println("\nInput: " + s);
System.out.println("There are " + parser.countTokens() + 
		   " entries in the path");
// COM
try {
    while(parser.hasMoreTokens()) {
	System.out.println(parser.nextToken());
    }
} catch (NoSuchElementException e) {
    System.out.println(e);
}
// END main1
}
{
    // uses enum interfaces
// START main2
String s = "c:\\windows\\command;c:\\dos;c:\\bin;c:\\util";
StringTokenizer parser = new StringTokenizer(s, ";");
try
 {
    while(parser.hasMoreElements()) {
	System.out.println(parser.nextElement());
    }
} catch (NoSuchElementException e) {
    System.out.println(e);
}
// END main2
}
{
// Example that uses multiple separators
// START count
String s = "a = b + c, d = e.";
StringTokenizer parser = new StringTokenizer(s, ",.", true);
// COM
System.out.println("\nInput: " + s);
// COM
System.out.println("There are " + parser.countTokens() + " tokens");
try {
    while (parser.hasMoreTokens()) {
	System.out.println(parser.nextToken());
    }
} catch (NoSuchElementException e) {
    e.printStackTrace();
}
// END count
}
{
// START quote
String s = "frontstuff 'Welcome to our Home' endstuff";
StringTokenizer parser = new StringTokenizer(s, " '", true);
String token, new_delimiter = null;
boolean look_for_matching_quote = false;
// COM
System.out.println("\nInput: " + s);
// COM
try {
    while (parser.hasMoreTokens()) { 
	if (new_delimiter != null) {
	    token = parser.nextToken(new_delimiter);
	    new_delimiter = null;
	} else {
	    token = parser.nextToken();
	}
	if (token.equals(" "))
	    continue;
	if (token.equals("'")) {
	    if (look_for_matching_quote) {
		new_delimiter = " '";
		look_for_matching_quote = false;
	    } else {
		new_delimiter = "'";  // can have embedded blanks
		look_for_matching_quote = true;
	    }
	} else {
	    System.out.println(token);
	}
    }
} catch (NoSuchElementException e) {
    e.printStackTrace();
}
// END quote
}
}
}
