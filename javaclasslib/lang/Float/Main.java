class Main {
    public static void main(String[] args) {
    {
// START max
// test if number is less than MAX_VALUE
float fnum = 3.1415927f;
if (fnum < Float.MAX_VALUE)
    fnum *= 100;
// END max
}
{
// START min
// test if number is greater than MIN_VALUE
float fnum = 2.71828f;
if (fnum > Float.MIN_VALUE)
    fnum = 1/fnum;
// END min
}
{
// START nan
float fnum = Float.NaN;
// test if number is Not-A-Number
if (Float.isNaN(fnum))		// succeeds
    System.out.println("correct");
if (Float.isNaN(Float.NaN))	// succeeds
    System.out.println("correct");

// A NaN is not equal to itself except when using equals()
if (fnum == Float.NaN)		// fails
    System.out.println("incorrect");
Float f1 = new Float(Float.NaN);
Float f2 = new Float(Float.NaN);
if (f1.equals(f2))		// succeeds
    System.out.println("correct");
// END nan
}
{
// START neginf
float fnum;
// COM
fnum = 0;
// COM
// UNCOM ...
// reset to 0 if number reached neg infinity
if (fnum == Float.NEGATIVE_INFINITY)
    fnum = 0;
// END neginf
}
{
// START posinf
float fnum;
// COM
fnum = 0;
// COM
// UNCOM ...
// reset to 0 if number reached pos infinity
if (fnum == Float.POSITIVE_INFINITY)
    fnum = 0;
// END posinf
}
// START const
Float pi = new Float(3.14159f);	// using float
Float num = new Float(8.23e13);	// using double
if (num.equals(pi))
// UNCOM ...
// COM
;
// COM
try {
    Float f = new Float("1.23"); // using string
// UNCOM 	...
// COM
System.out.println("f is " + f);
// COM
} catch (NumberFormatException e) {
// UNCOM ...
}
// END const
{
// START toint
float fnum = 22.45915f;
int bitrepr = Float.floatToIntBits(fnum);      // get bit repr
float reconst = Float.intBitsToFloat(bitrepr); // reconstruct fnum

if (reconst == fnum) // should be true
    System.out.println("correct");
// END toint
}
// START float
Float fobj = new Float(0.47712f);

double dval = fobj.doubleValue(); // 0.47712001249449066
float fval = fobj.floatValue();   // 0.47712
int ival = fobj.intValue();       // round to 0
long lval = fobj.longValue();     // round to 0
byte bval = fobj.byteValue();     // round to 0
short sval = fobj.shortValue();   // round to 0
// END float
System.out.println("float: " + dval);
System.out.println("float: " + fval);
System.out.println("integer: " + ival);
System.out.println("long: " + lval);
System.out.println("short: " + sval);
System.out.println("byte: " + bval);

{
// START equals
Float f1 = new Float(4.8123f);
Float f2 = new Float(4.8123f);

// Check whether the value of two Floats are equal
if (f1.equals(f2))
    System.out.println("equal");
// END equals
}
{
    int tabsize = 13;
    int[] hits = new int[13];
// START hash
Float fnum = new Float(1.61803f);
int hashval = fnum.hashCode();	// generate hash code
++hits[Math.abs(hashval%tabsize)]; // count hits
// END hash
}
{
// START isInf
Float fnum = new Float(1.6878f);
// UNCOM ...
if (fnum.isInfinite())	// method version
    throw new ArithmeticException();
if (Float.isInfinite(85.1f))    // class version
// UNCOM    break;
// END isInf
    ;
}
{
// START isnan
Float fnum = new Float(1.523E24f);
if (fnum.isNaN())		   // method version
    throw new ArithmeticException();
if (Float.isNaN(fnum.floatValue())) // class version
// UNCOM ...
// END isnan
;
}
{
// START str
Float fnum = new Float(0.843f);
String str = fnum.toString();	// get string form of number
String str2 = Float.toString(1.523E24f);
String pstr = "The two floats are " + str + " and " + str2;
// END str
System.out.println(pstr);
}
{
// START value
String str = "1.0871E3f";
try {
    Float r = Float.valueOf(str);
// COM
    System.out.println(r);
// COM
// UNCOM     ...
} catch (NumberFormatException e) {
    System.err.println("Could not convert string to number " + str);
}
// END value
}
}
}
