import java.util.Properties;
class Main {
    public static void main(String[] args) {
    {
// START max
// test if number is less than MAX_VALUE
long lnum = 512;
if (lnum < Long.MAX_VALUE)
    lnum *= 1000;
// END max
}
{
// START min
// test if number is greater than MIN_VALUE
long lnum = -512;
if (lnum > Long.MIN_VALUE)
    lnum -= 1000;
// END min
}
{
// START const
Long lobj = new Long(1024);	      // using long
try {
    Long lobj2 = new Long("1048576"); // using string
    long div = lobj2.longValue() / lobj.longValue();
// COM
System.out.println("divided is " + div);
// COM
} catch (NumberFormatException e) {
// UNCOM ...
}
// END const
// START long
Long longobj = new Long(69121);

double dval = longobj.doubleValue(); // 69121.0
float fval = longobj.floatValue();   // 69121.0
int ival = longobj.intValue();       // 69121
long lval = longobj.longValue();     // 69121
byte bval = longobj.byteValue();     // 1
short sval = longobj.shortValue();   // 3685
// END long
System.out.println("double: " + dval);
System.out.println("float: " + fval);
System.out.println("integer: " + ival);
System.out.println("long: " + lval);
System.out.println("byte: " + bval);
System.out.println("short: " + sval);

{
// START equals
Long l1 = new Long(35661);
Long l2 = new Long(2341);

// Check whether the value of two Longs are equal
if (l1.equals(l2))
    System.out.println("equal");
// END equals
}
{
    int tabsize = 13;
    int[] hits = new int[13];
// START hash
Long lnum = new Long(3290);
int hashval = lnum.hashCode();     // generate hash code
++hits[Math.abs(hashval%tabsize)]; // count hits
// END hash
}

{
// START getlong
// set up properties 
Properties props = System.getProperties();
props.put("test.bignum", "1048576");       // radix 10 int property
props.put("test.bighex", "0xeffffffffff"); // radix 16 int property
props.put("test.hex2", "#2");              // radix 16 int property
props.put("test.octal", "065");            // radix 8 int property
props.put("test.nonnum", "2.5.1");         // non-int property
System.setProperties(props);

// use the three forms of getInteger()
Long p1 = Long.getLong("test.bignum");
Long p2 = Long.getLong("test.bighex", 1024);
Long p3 = Long.getLong("test.hex2", new Long(0));
Long p4 = Long.getLong("test.octal", 256);
Long p5 = Long.getLong("test.nonnum", 1);

System.out.println("bignum: " + p1);
System.out.println("bighex: " + p2);
System.out.println("hex2: " + p3);
System.out.println("octal: " + p4);
System.out.println("nonnum: " + p5);
// END getlong
}
{
// START parselong
try {
    long lnum = Long.parseLong("8861212097");
    long hexnum = Long.parseLong("8a24fe3", 16);
// COM
System.out.println("parse " + lnum + " " + hexnum);
// COM
} catch (NumberFormatException e) {
// UNCOM ...
}
// END parselong
}
{
// START str
Long lnum = new Long(7981828);
String str = lnum.toString();                // get string form of Long obj
String str2 = Long.toString(312123412);	     // string of number
String str3 = Long.toString(123318811, 16);  // string of hex 
String pstr = "The three numbers are " + str + ", " + str2 + " ," + str3;
// END str
System.out.println(pstr);
}
{
// START value
String str = "89618291243";
try {
    Long l1 = Long.valueOf(str);     // parse number in radix 10
    Long l2 = Long.valueOf(str, 16); // parse number in radix 16
// COM
    System.out.println("value : " + l1 + ", " + l2);
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
