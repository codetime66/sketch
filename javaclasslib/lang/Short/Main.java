import java.util.Properties;
class Main {
    public static void main(String[] args) {
    {
// START max
// test if number is less than MAX_VALUE
short s0 = 32;
if (s0 < Short.MAX_VALUE)
    s0 += 500;
// END max
}
{
// START min
// test if number is greater than MIN_VALUE
short s0 = -12;
if (s0 > Short.MIN_VALUE)
    s0 -= 5;
// END min
}
{
// START Short
Short width = new Short((short)12); // using integer
try {
    Short height = new Short("22"); // using string
    int area = width.intValue() * height.shortValue();
// COM
System.out.println("area is " + area);
// COM
} catch (NumberFormatException e) {
// UNCOM ...
}
// END Short
{
// START double
Short sobj = new Short((short)118);

byte bval = sobj.byteValue();     // 118.0
double dval = sobj.doubleValue(); // 118.0
float fval = sobj.floatValue();   // 118
int ival = sobj.intValue();       // 118
long lval = sobj.longValue();     // 118
short sval = sobj.shortValue();   // 118
// END double
System.out.println("double: " + dval);
System.out.println("float: " + fval);
System.out.println("integer: " + ival);
System.out.println("long: " + lval);
System.out.println("short: " + sval);
System.out.println("byte: " + bval);
}

{
// START equals
Short s1 = new Short((short)92);
Short s2 = new Short((short)92);

// Check whether the value of two Shorts are equal
if (s1.equals(s2))
    System.out.println("equal");
// END equals
}
{
    int tabsize = 13;
    int[] hits = new int[13];
// START hash
Short s0 = new Short((short)39);
int hashval = s0.hashCode();	    // generate hash code
++hits[Math.abs(hashval%tabsize)];  // count hits
// END hash
}

{
// START str
Short sobj = new Short((short)25);
String str = sobj.toString();             // string of Short
String str2 = Short.toString((short)112); // string of short
String pstr = "The two numbers are " + str + ", " + str2;
// END str
System.out.println(pstr);
}
{
// START value
String str = "82";
try {
    Short s1 = Short.valueOf(str);     // parse number in radix 10
    Short s2 = Short.valueOf(str, 16); // parse number in radix 16
// COM
    System.out.println("value : " + s1 + ", " + s2);
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
