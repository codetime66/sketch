class Main {
    public static void main(String[] args) {
    {
// START max
// test if number is less than MAX_VALUE
double dnum = 3.1415927;
if (dnum < Double.MAX_VALUE)
    dnum *= 100;
// END max
}
{
// START min
// test if number is greater than MIN_VALUE
double dnum = 2.71828;
if (dnum > Double.MIN_VALUE)
    dnum = 1/dnum;
// END min
}
{
// START nan
double dnum = Double.NaN;
// test if number is Not-A-Number
if (Double.isNaN(dnum))		// succeeds
    System.out.println("correct");
if (Double.isNaN(Double.NaN))	// succeeds
    System.out.println("correct");

// A NaN is not equal to itself except when using equals()
if (dnum == Double.NaN)		// fails
    System.out.println("incorrect");
Double d1 = new Double(Double.NaN);
Double d2 = new Double(Double.NaN);
if (d1.equals(d2))		// succeeds
    System.out.println("correct");
// END nan
}
{
// START neginf
double dnum;
// COM
dnum = 0;
// COM
// UNCOM ...
// reset to 0 if number reached neg infinity
if (dnum == Double.NEGATIVE_INFINITY)
    dnum = 0;
// END neginf
}
{
// START posinf
double dnum;
// COM
dnum = 0;
// COM
// UNCOM ...
// reset to 0 if number reached pos infinity
if (dnum == Double.POSITIVE_INFINITY)
    dnum = 0;
// END posinf
}
// START const
// Create new Double object called 'pi'
Double pi = new Double(3.14159);
try {
    // Create new Double object using a string
    Double dobj = new Double("29.5");
    if (dobj.equals(pi))
// UNCOM 	...
// COM
;
// COM
} catch (NumberFormatException e) {
// UNCOM ...
}
// END const
;
{
// START tolong
double dnum = 22.45915;
long bitrepr = Double.doubleToLongBits(dnum);      // get bit repr
double reconst = Double.longBitsToDouble(bitrepr); // reconstruct dnum

if (reconst == dnum) // should be true
    System.out.println("correct");
// END tolong
}
// START double
Double dobj = new Double(1923311.47712);

double dval = dobj.doubleValue(); // 1923311.47712 
float fval = dobj.floatValue();   // 1923311.5
int ival = dobj.intValue();       // 1923311
long lval = dobj.longValue();     // 1923311
byte bval = dobj.byteValue();     // -17
short sval = dobj.shortValue();   // 22767
// END double
System.out.println("double: " + dval);
System.out.println("float: " + fval);
System.out.println("integer: " + ival);
System.out.println("long: " + lval);
System.out.println("byte: " + bval);
System.out.println("short: " + sval);

{
// START equals
Double d1 = new Double(4.8123);
Double d2 = new Double(4.8123);

// Check whether the value of two Doubles are equal
if (d1.equals(d2))
    System.out.println("equal");
// END equals
}
{
    int tabsize = 13;
    int[] hits = new int[tabsize];
// START hash
// Keep track of hits on hash code
Double dnum = new Double(1.61803);
int hashval = dnum.hashCode();
++hits[Math.abs(hashval%tabsize)];  // count hits
// END hash
}
{
// START isInf
Double dnum = new Double(1.6878);
// UNCOM ...
if (dnum.isInfinite())	// method version
    throw new ArithmeticException();
if (Double.isInfinite(85.1))    // class version
// UNCOM    break;
// END isInf
    ;
}
{
// START isnan
Double dnum = new Double(1.523E24);
if (dnum.isNaN())		   // method version
    throw new ArithmeticException();
if (Double.isNaN(dnum.doubleValue())) // class version
// UNCOM ...
// END isnan
;
}
{
// START str
Double dnum = new Double(0.843);
String str = dnum.toString();	// get string form of Double obj
String str2 = Double.toString(0.432); // string form of double
String pstr = "The values are " + str + ", " + str2;
// END str
System.out.println(pstr);
}
{
// START value
String str = "1.0871E3";
try {
    Double r = Double.valueOf(str);
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
