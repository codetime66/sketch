import java.util.Properties;
class Main {
    public static void main(String[] args) {
    {
// START max
// test if number is less than MAX_VALUE
byte b0 = 32;
if (b0 < Byte.MAX_VALUE)
    b0 += 5;
// END max
}
{
// START min
// test if number is greater than MIN_VALUE
byte b0 = -12;
if (b0 > Byte.MIN_VALUE)
    b0 -= 5;
// END min
}
{
// START byte
Byte width = new Byte((byte)12);  // using byte
try {
    Byte height = new Byte("22"); // using string
    int area = width.intValue() * height.shortValue();
// COM
System.out.println("area is " + area);
// COM
} catch (NumberFormatException e) {
// UNCOM ...
}
// END byte
// START double
Byte iobj = new Byte((byte)118);

byte bval = iobj.byteValue();     // 118.0 
double dval = iobj.doubleValue(); // 118.0
float fval = iobj.floatValue();   // 118
int ival = iobj.intValue();       // 118
long lval = iobj.longValue();     // 118
short sval = iobj.shortValue();   // 118
// END double
System.out.println("double: " + dval);
System.out.println("float: " + fval);
System.out.println("integer: " + ival);
System.out.println("long: " + lval);
System.out.println("short: " + sval);
System.out.println("byte: " + bval);

{
// START equals
Byte b1 = new Byte((byte)92);
Byte b2 = new Byte((byte)92);

// Check whether the value of two Bytes are equal
if (b1.equals(b2))
    System.out.println("equal");
// END equals
}
{
    int tabsize = 13;
    int[] hits = new int[13];
// START hash
Byte b0 = new Byte((byte)39);
int hashval = b0.hashCode();	    // generate hash code
++hits[Math.abs(hashval%tabsize)];  // count hits
// END hash
}

{
// START str
Byte bobj = new Byte((byte)25);
String str = bobj.toString();           // string of Byte
String str2 = Byte.toString((byte)112);	// string of byte
String pstr = "The two numbers are " + str + ", " + str2;
// END str
System.out.println(pstr);
}
{
// START value
String str = "82";
try {
    Byte b1 = Byte.valueOf(str);     // parse number in radix 10
    Byte b2 = Byte.valueOf(str, 16); // parse number in radix 16
// COM
    System.out.println("value : " + b1 + ", " + b2);
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
