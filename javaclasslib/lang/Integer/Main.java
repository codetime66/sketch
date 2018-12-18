import java.util.Properties;
class Main {
    public static void main(String[] args) {
    {
// START max
// test if number is less than MAX_VALUE
int inum = 512;
if (inum < Integer.MAX_VALUE)
    inum += 16344;
// END max
}
{
// START min
// test if number is greater than MIN_VALUE
int inum = -512;
if (inum > Integer.MIN_VALUE)
    inum *= 100;
// END min
}
{
// START const
Integer width = new Integer(240);	// using integer
try {
    Integer height = new Integer("360"); // using string
    int area = width.intValue() * height.intValue();
// COM
System.out.println("area is " + area);
// COM
} catch (NumberFormatException e) {
// UNCOM ...
}
// END const
// START integer
Integer iobj = new Integer(855);

double dval = iobj.doubleValue(); // 855.0
float fval = iobj.floatValue();   // 855.0
int ival = iobj.intValue();       // 855
long lval = iobj.longValue();     // 855
byte bval = iobj.byteValue();     // 87
short sval = iobj.shortValue();   // 855
// END integer
System.out.println("double: " + dval);
System.out.println("float: " + fval);
System.out.println("integer: " + ival);
System.out.println("long: " + lval);
System.out.println("long: " + bval);
System.out.println("long: " + sval);

{
// START equals
Integer i1 = new Integer(192);
Integer i2 = new Integer(192);

// Check whether the value of two Integers are equal
if (i1.equals(i2))
    System.out.println("equal");
// END equals
}
{
    int tabsize = 13;
    int[] hits = new int[13];
// START hash
Integer inum = new Integer(3290);
int hashval = inum.hashCode();	// generate hash code
++hits[Math.abs(hashval%tabsize)];  // count hits
// END hash
}
{
// START getint
// set up properties 
Properties props = System.getProperties();
props.put("os.maxusers", "250");  // radix 10 int property
props.put("os.maxfiles", "0xff"); // radix 16 int property
props.put("os.maxprinters", "#2a"); // radix 16 int property
props.put("os.maxfd", "065"); // radix 8 int property
props.put("os.version", "2.5.1"); // non-int property
System.setProperties(props);

// use the three forms of getInteger()
Integer maxusers = Integer.getInteger("os.maxusers");
Integer maxfiles = Integer.getInteger("os.maxfiles", 1024);
Integer maxprs = Integer.getInteger("os.maxprinters",
				    new Integer(1));
Integer maxfd = Integer.getInteger("os.maxfd", 256);
Integer vers = Integer.getInteger("os.version", 1);
if (maxusers != null)
    System.out.println("max users: " + maxusers);
System.out.println("max files: " + maxfiles);
System.out.println("max printers: " + maxprs);
System.out.println("max fds: " + maxfd);
System.out.println("os version: " + vers);
// END getint
}
{
// START parseint
try {
    int i = Integer.parseInt("25");
    int octnum = Integer.parseInt("065", 8);
// COM
System.out.println("parse " + i + " " + octnum);
// COM
} catch (NumberFormatException e) {
// UNCOM ...
}
// END parseint
}
{
// START str
Integer inum = new Integer(25);
String str = inum.toString();  // get string form of Integer obj
String str2 = Integer.toString(312);	// string of number
String str3 = Integer.toString(97, 8);  // string of octal number
String pstr = "The three numbers are " + str + ", " + str2 + " ," + str3;
// END str
System.out.println(pstr);
}
{
// START value
String str = "1243";
try {
    Integer i = Integer.valueOf(str);    // parse number in radix 10
    Integer i2 = Integer.valueOf(str, 16); // parse number in radix 16
// COM
    System.out.println("value : " + i + ", " + i2);
// COM
// UNCOM     ...
} catch (NumberFormatException e) {
    System.err.println("Could not convert string to number " + str);
}
// END value
}
}
}
}
