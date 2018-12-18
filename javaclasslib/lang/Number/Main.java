// START main1
import java.util.Vector;
import java.util.Enumeration;

class Main {
// END main1

// START float
// Calculate the float sum of a vector of Number objects
public static float floatSum(Vector v) {
float sum = 0;
for (Enumeration e = v.elements();
     e.hasMoreElements();
     sum += ((Number)e.nextElement()).floatValue());
return (sum);
}
// END float

// START byte
// Calculate the byte sum of a vector of Number objects
public static int byteSum(Vector v) {
byte sum = 0;
for (Enumeration e = v.elements();
     e.hasMoreElements();
     sum += ((Number)e.nextElement()).byteValue());
return (sum);
}
// END byte

// START short
// Calculate the short sum of a vector of Number objects
public static int shortSum(Vector v) {
short sum = 0;
for (Enumeration e = v.elements();
     e.hasMoreElements();
     sum += ((Number)e.nextElement()).shortValue());
return (sum);
}
// END short

// START int
// Calculate the int sum of a vector of Number objects
public static int intSum(Vector v) {
int sum = 0;
for (Enumeration e = v.elements();
     e.hasMoreElements();
     sum += ((Number)e.nextElement()).intValue());
return (sum);
}
// END int

// START long
// Calculate the long sum of a vector of Number objects
public static long longSum(Vector v) {
long sum = 0;
for (Enumeration e = v.elements();
     e.hasMoreElements();
     sum += ((Number)e.nextElement()).longValue());
return (sum);
}
// END long

/*
// START double
// Calculate the double sum of a vector of Number objects
public static double doubleSum(Vector v) {
double sum = 0;
for (Enumeration e = v.elements();
     e.hasMoreElements();
     sum += ((Number)e.nextElement()).doubleValue());
return (sum);
}
// END double
*/
// START main2
    // Calculate the double sum of a vector of Number objects
    public static double doubleSum(Vector v) {
	double sum = 0;
	for (Enumeration e = v.elements();
	     e.hasMoreElements();
	     sum += ((Number)e.nextElement()).doubleValue());
	return (sum);
    }
    public static void main(String[] args) {
	Vector numvec = new Vector(6);

	// Prepare contents of vector
	numvec.addElement(new Integer(1995));
	numvec.addElement(new Double(6.4e3));
	numvec.addElement(new Integer(32));
	numvec.addElement(new Float(7.821e3f));
	numvec.addElement(new Double(22.32e2));
	numvec.addElement(new Byte((byte)12));
	numvec.addElement(new Short((short)1997));
	numvec.addElement(new Long(10424300));
	numvec.addElement(new java.math.BigDecimal("1923.19009"));
	numvec.addElement(new java.math.BigInteger("104243001231239879"));

	System.out.println("double total: " + doubleSum(numvec));
// COM
	System.out.println("float total: " + floatSum(numvec));
	System.out.println("long total: " + longSum(numvec));
	System.out.println("int total: " + intSum(numvec));
	System.out.println("short total: " + shortSum(numvec));
	System.out.println("byte total: " + byteSum(numvec));
	long lnum = Long.MAX_VALUE;
	Long lobj = new Long(lnum);
	int inum = lobj.intValue();
	System.out.println(lnum + " " + inum);
// COM
    }
}
// END main2
