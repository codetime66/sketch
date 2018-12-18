import java.io.*;
import java.util.Vector;

class Main {
// START starts
// Method that parses string of octal, hex, or decimal number
public static int parseNumber(String str) throws NumberFormatException {
    if (str.startsWith("0x")) {
        return Integer.parseInt(str.substring(2), 16);
    }
    if (str.startsWith("#")) {
        return Integer.parseInt(str.substring(1), 16);
        }
    if (str.startsWith("0")) {
        return Integer.parseInt(str.substring(1), 8);
        }
    return Integer.parseInt(str, 10);
}

// Returns true if file has 'htm' or 'html' or 'htm'* suffix
public static boolean isHTML(String name) {
    int dotPosn = name.indexOf('.'); // file type starting point
    if (dotPosn >= 0)
        return (name.startsWith("htm", dotPosn + 1));
    return (false);
}
// END starts

// START replace
// method that replaces blanks with '#'
public static String blanksWithHash(String str) {
    return (str.replace(' ', '#'));
}
// END replace
// START charat
// method that returns true if 'str' contains a white
// space character
public static boolean hasWhiteSpace(String str) {
    for (int i = 0; i < str.length(); i++) {
        if (Character.isWhitespace(str.charAt(i)))
            return (true);
    }
    return (false);
}
// END charat    
// START ends
// method that checks whether 'file' has correct suffix
// to be Java source file
public static boolean validSrcFilename(String file) {
    return (file.endsWith(".java"));
}
// END ends
// START getbytes
// method that writes out a String in default and "Cp037"
public static void writeString(OutputStream out, String str) {
    try {
        byte[] buf = str.getBytes();
        byte[] utf = str.getBytes("UTF8");
        out.write(buf);
    } catch (IOException e) {
        System.err.println(e);
    }
}
// END getbytes
// START tochars
// return an upper case version of a string
public static String turnUpper(String str) {
    char[] contents = str.toCharArray();
    for(int i = 0; i < contents.length; i++) {
        contents[i] = Character.toUpperCase(contents[i]);
    }
    return (new String(contents));
}
// END tochars
// START getchars
// method that splits a string into two and returns the head
public static String firstHalf(String str) {
    int half = str.length() / 2;
    char[] buf = new char[half];
    str.getChars(0, half, buf, 0);
    return (new String(buf));
}
// END getchars
// START index
// Method that counts number of tokens between separator char
public static int countTokens(String str, char separator) {
    int count, offset, where;
    
    for (count = 0, offset = 0;
         ((where = str.indexOf(separator, offset)) >= 0);
         offset = where + 1) {
        if (where > 0)
            ++count;
    }
    if (str.charAt(str.length()-1) != separator)
        ++count;
    return (count);
}

// Method that counts number of tokens between separator string
public static int countTokens(String str, String separator) {
    int count, offset, where;
    
    for (count = 0, offset = 0;
         ((where = str.indexOf(separator, offset)) >= 0);
         offset = where + separator.length()) {
        if (where > 0)
            ++count;
    }
    if (!str.endsWith(separator))
        ++count;
    return (count);
}
// END index
// START lastind
// Get file name without parent directories
public static String getAtomicFilename(String pathname, char separator) {
    int lastSep = pathname.lastIndexOf(separator);
    if (lastSep >= 0)
        return new String(pathname.substring(lastSep + 1, pathname.length()));
    else
        return new String(pathname); // atomic to begin with
}

// Find directory path of pathname
public static String getDirPath(String pathname, String separator) {
    // search pathname starting from index at end of pathname
    int lastSep = pathname.lastIndexOf(separator, pathname.length()-1);
    if (lastSep >= 0)
        return new String(pathname.substring(0, lastSep));
    else
        return null; // no directories in pathname
}
// END lastind

    public static void main (String[] args) {
    {
// START main
String str1 = "abc";
char data[] = {'a', 'b', 'c'};
String str2 = new String(data);

String str3 = str1.concat(str2);
String str4 = str1 + str2;
// END main
System.out.println("1: " + str1 + " 2: " + str2);
System.out.println("3: " + str3 + " 4: " + str4);
if (hasWhiteSpace("abc") || !hasWhiteSpace("a b c"))
    System.out.println("charat error");
else
    System.out.println("charat correct");
// for endsWith
if (validSrcFilename("String.java") && !validSrcFilename("String.class"))
    System.out.println("endswith correct");
else
    System.out.println("endswith error");

// for getBytes
writeString(System.out, "this is a test of getBytes\n");
// for getChars
System.out.println("testing getchars: " + firstHalf("abcdef"));
// for indexOf
System.out.println("index (5) :" + countTokens("a bc def gh i", ' '));
System.out.println("index (4) :" + countTokens("a--bc-def--gh--i--", "--"));
System.out.println("index (4) :" + countTokens("--a--bc-def--gh--i--", "--"));
// for lastindex
System.out.println("lastIndex: " + getAtomicFilename("/a/c/d", '/'));
System.out.println("lastIndex: " + getDirPath("/a/c/d", "/"));
System.out.println("lastIndex: " + getAtomicFilename("a", '/'));
System.out.println("lastIndex: " + getDirPath("a", "/"));
// for replace
System.out.println("replace: " + blanksWithHash("this is a test"));
// for starts
System.out.println("starts with: " + parseNumber("023"));
System.out.println("starts with: " + isHTML("abc.html"));
System.out.println("starts with: " + isHTML("abc.txt"));
// for tochars
System.out.println("tochars: " + turnUpper("This is a test"));
}
{
// START compare
String str = "this is a test";

int r1 = str.compareTo("this is a test and more"); // negative (str shorter)
int r2 = str.compareTo("this is not a test");      // negative ('a' < 'n')
int r3 = str.compareTo("this is a test");          // 0
int r4 = str.compareTo("no, this is not a test");  // positive ('t' > 'n')
int r5 = str.compareTo("this");                    // positive (str longer)
// END compare
System.out.println("compare: " + r1 + " " + r2 + " " + r3 
                   + " " + r4 + " " + r5);
}
{
// START concat
String str1 = "abc";

String str2 = str1.concat("cde"); // "abccde"
String str3 = str1 + "cde";       // "abccde"
// END concat
System.out.println("concat: " + str2 + " " + str3);
}
{
// START copy
char data[] = {'a', 'b', 'c'};
String abc = String.copyValueOf(data);
String bc = String.copyValueOf(data, 1, 2);
System.out.println("copy " + abc + " " + bc);
data[2] = 'e';  // updating 'data' doesn't affect abc or bc
System.out.println("copy after " + abc + " " + bc);
// END copy
}
{
// START equals
String abc = "abc";
String str = new String(abc);
if (str.equals(abc))
    System.out.println("correct");
// END equals
else
    System.out.println("equals incorrect");
}
{
// START equalsic
String abc = "abc";
String str = new String("ABC");
if (str.equalsIgnoreCase(abc))
    System.out.println("correct");
// END equalsic
else 
    System.out.println("equals ic incorrect");
// END equalsic
}
{
    int tabsize = 13;
    int[] hits = new int[tabsize];
// START hash
// Keep track of hits on hash code
String str = "this is a test";
int hashval = str.hashCode();       // get hash code for str
++hits[Math.abs(hashval%tabsize)];  // count hits
// END hash
}
{
String s1 = "abc";
String s2 = new String (s1);
if (s1 == s2)
    System.out.println("references are equal");
else
    System.out.println("references are not equal");
// START intern
// intern() values the same means strings are equal
if (s1.intern() == s2.intern())
    System.out.println("Strings are equal");
// END intern
}
{
// START region
String s1 = "this is a test";
String s2 = "testing";
String s3 = "Testing";
int testLoc = s1.indexOf("test", 0);
if (testLoc >= 0) {
    if (s1.regionMatches(testLoc, s2, 0, 4))
        System.out.println("regions match");
    // try again using case-insensitive match
    if (s1.regionMatches(true, testLoc, s3, 0, 4))
        System.out.println("regions match when case ignored");
}
// END region
}
{
// START const
String s1 = new String();               // empty string
String s2 = "abc";                      // use constant
String s3 = new String(s2);             // copy from String

s2 = "def";                             // won't affect s3


char[] charArray = {'a', 'b', 'c'};     // copy from char array
String s4 = new String(charArray);      // "abc"

charArray[1] = 'B';                     // won't affect s4
String s5 = new String(charArray, 1, 1);// "B"

// translate from byte array, using default encoding
byte[] byteArray = {'a', 'b', 'c'};
String s6 = new String(byteArray);      // "abc"

byteArray[1] = 'x';                     // won't affect s6
String s7 = new String(byteArray, 1, 1);// "x"

String s8 = null;
try {
    // translate from byte array, using  utf-8 encoding
    byte[] utfbuf = {(byte)110, (byte)117, (byte)109,
                         (byte)-61, (byte)-87, (byte)114, (byte)111};
    s8 = new String(utfbuf, "UTF8");
} catch (UnsupportedEncodingException e) {
    e.printStackTrace();
}

// copy from StringBuffer
StringBuffer buf = new StringBuffer("xyz");
String s9 = new String(buf);

buf.setLength(1); // update to buf won't affect s8
// END const
System.out.println("constructors:");
System.out.println(s1);
System.out.println(s2);
System.out.println(s3);
System.out.println(s4);
System.out.println(s5);
System.out.println(s6);
System.out.println(s7);
System.out.println(s8);
System.out.println(s9);
}
{
// START tolower
// creates new string with all lower case
System.out.println("This is a test".toLowerCase());
// END tolower
// START toupper
// creates new string with all upper case
System.out.println("This is a test".toUpperCase());
// END toupper
// START tostring
String s1 = "abc";
String s2 = s1.toString();
// s2 is just reference of s1
if (s1 == s2)   
    System.out.println("same thing");
// END tostring
}
{
// START trim
String s1 = "  Start and end.  ";
String s2 = s1.trim(); // "Start and end."
// END trim
System.out.println("trim: " + s1);
System.out.println("trim: " + s2);
}
{
// START valueof
int inum = 512;
long lnum = Long.MAX_VALUE;
double dnum = 123.123e54;
float fnum = 3.1243f;
Object obj1 = null;
Object obj2 = new Vector();

String s1 = String.valueOf(true); // "true"
String s2 = String.valueOf('A');  // "A"
String s3 = String.valueOf(inum); // "512"
String s4 = String.valueOf(lnum); // "9223372036854775807"
String s5 = String.valueOf(dnum); // "1.23123e+56"
String s6 = String.valueOf(fnum); // "3.1243"
String s7 = String.valueOf(obj1); // "null"
String s8 = String.valueOf(obj2); // "[]"

char[] charArray = {'a', 'b', 'c'};
String s9 = String.valueOf(charArray);  // "abc"
charArray[1] = 'B'; // won't affect s9
// END valueof
System.out.println("valueof");
System.out.println(s1);
System.out.println(s2);
System.out.println(s3);
System.out.println(s4);
System.out.println(s5);
System.out.println(s6);
System.out.println(s7);
System.out.println(s8);
System.out.println(s9);
}
}
}

