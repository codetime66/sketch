import java.util.Vector;
class Main2 {
// START append
// Return one string that represents the path constructed
// using the components, each separated by 'sep'
// e.g. {{a}, {b}, {c}}, '/' -> /a/b/c
public static String stringify(char[][]components, char sep) {
    StringBuffer str = new StringBuffer();
    for (int i = 0; i < components.length; i++) {
        str.append(sep).append(components[i]);
    }
    return (str.toString());
}
// END append
// START getchars
// Make a copy of StringBuffer
public static StringBuffer copyStringBuffer(StringBuffer buf) {
    char[] data = new char[buf.length()];
    buf.getChars(0, buf.length(), data, 0);
    StringBuffer answer = new StringBuffer(buf.length());
    answer.append(data);
    return (answer);
}
// END getchars
// START setchar
// Set null characters in buffer to '#'
public static void markNullChars(StringBuffer buf)
{
    for (int i = 0; i < buf.length(); i++) {
        if (buf.charAt(i) == '\0')
            buf.setCharAt(i, '#');
    }
}
// END setchar
// START charat
// Count null characters in buffer
public static int countNullChars(StringBuffer buf)
{
    int count = 0;
    for (int i = 0; i < buf.length(); i++) {
        if (buf.charAt(i) == '\0')
            ++count;
    }
    return (count);
}
// END charat
public static void main (String[] args) {
{
// for append
    char[][] comps = {{'a'}, {'b'}, {'c'}};
    System.out.println(stringify(comps, '/'));
// for charAt
StringBuffer s1 = new StringBuffer("this is a test");
s1.setLength(30);
System.out.println("charAt: nulls(16) = " + countNullChars(s1));
// for setchar
markNullChars(s1);
System.out.println("setChar: " + s1);
// for getchars
StringBuffer s2 = copyStringBuffer(s1);
System.out.println("getchars clone: " + s2);
}
/*
// START copy
public synchronized void setCharAt(int index, char ch) {
if ((index < 0) || (index >= count)) {
    throw new StringIndexOutOfBoundsException(index);
}
copyWhenShared();
value[index] = ch;
}
// END copy
*/
{
// START capacity
StringBuffer s0 = new StringBuffer();
StringBuffer s1 = new StringBuffer(20);
StringBuffer s2 = new StringBuffer("this is a test");

// capacity is different from length
// for s0 (cap 16, len 0)
// for s1 (cap 20, len 0), 
// for s2 (cap 16+14, len 14)
System.out.println("s0: capacity " + s0.capacity() + " length " + s0.length());
System.out.println("s1: capacity " + s1.capacity() + " length " + s1.length());
System.out.println("s2: capacity " + s2.capacity() + " length " + s2.length());
// END capacity
}
{
// START setlen
StringBuffer s1 = new StringBuffer("this is a test");

// expand buffer
s1.setLength(20);
System.out.println(s1 + "(" + s1.length() + ")"); // added null chars

// truncate buffer
s1.setLength(4);
System.out.println(s1 + "(" + s1.length() + ")"); // "this"
// END setlen
}
{
// START ensure
StringBuffer s1 = new StringBuffer("this is a test");
String filler = new String("simple yet powerful");

s1.ensureCapacity(10); // no op, already cap > 10
s1.ensureCapacity(100);

// buffer need not be expanded for following operations
s1.append(filler).append(filler).append(filler);
// END ensure
System.out.println("ensure cap " + s1.capacity());
}
{
// START append2
int inum = 512;
long lnum = Long.MAX_VALUE;
double dnum = 123.123e54;
float fnum = 3.1243f;
char sep = ' ';
Object obj1 = null;
Object obj2 = new Vector();
char[] charArray = {'a', 'b', 'c'};
StringBuffer buf = new StringBuffer(100);

// Keep appending all the data types above to buf
buf.append(inum).append(sep).append(lnum).append(sep);
buf.append(dnum).append(sep).append(dnum).append(sep);
buf.append(obj1).append(sep).append(obj2);
buf.append(sep).append(charArray);
buf.append(sep).append(charArray, 1, 2);
// END append2
System.out.println("append2: " + buf.toString());
}
{
// START insert
int inum = 512;
long lnum = Long.MAX_VALUE;
double dnum = 123.123e54;
float fnum = 3.1243f;
char sep = ' ';
Object obj1 = null;
Object obj2 = new Vector();
char[] charArray = {'a', 'b', 'c'};
StringBuffer buf = new StringBuffer(100);

// Keep inserting at head of buffer all the data types above
buf.insert(0, inum).insert(0, sep).insert(0, lnum).insert(0, sep);
buf.insert(0, dnum).insert(0, sep).insert(0, dnum).insert(0, sep);
buf.insert(0, obj1).insert(0, sep).insert(0, obj2);
buf.insert(0, sep).insert(0, charArray);
// END insert
System.out.println("insert: " + buf.toString());
}

}
}
