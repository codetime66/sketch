import java.lang.Boolean;

class maxmin {
    public static int test() {
// START MAXMINV
char ch = 'a';
// UNCOM	...
if (ch <= Character.MAX_VALUE && ch >= Character.MIN_VALUE)
// UNCOM	...
// END MAXMINV
System.out.println("maxmin correct");
else
System.out.println("maxmin incorrect");
return (0);
}
}

class misc1 {
    public static int test() {
// START val
Character charobj = new Character('A');
char ch = charobj.charValue(); // returns ('A');
// END val
System.out.println("2: (expect 'A')" + ch);
return(0);
}
}

class eq1 {
    public static int test() {
// START EQUALS
Character c1 = new Character('\u23f3');
Character c2 = new Character('A');
if (c1.equals(c2))
// UNCOM 	...
// END EQUALS
System.out.println("incorrect");
{
// START hash
int[] hits = new int[1023];
Character cobj = new Character('A');
int hashval = cobj.hashCode();	// generate hash code
++hits[Math.abs(hashval%hits.length)]; // count hits
// END hash
}
return(0);
}
}

class digits {
// START digit
// Given a char array and radix, return its numeric value
// Assume array contains only valid digits
static int charsToNumber(char[] str, int radix) {
    int number = 0;
    for (int magnitude = 1, i = str.length - 1;
	 i >= 0;
	 magnitude *= radix, i--)
	number += (Character.digit(str[i], radix)) * magnitude;
    return number;
}
// END digit
     public static void test() {
	 char[] teststr = {'a', '1', '0'};
	 System.out.println("a10 base 16:" + charsToNumber(teststr, 16));
	 char [] teststr2 = {'1', '2'};
	 System.out.println("12 base 10:" + charsToNumber(teststr2, 10));
// START fordigit
char ch = Character.forDigit(10, 16); // returns 'A'
// END fordigit
System.out.println("10 in base 16 is: " + ch);
int number = 0;
// START isdigit
if (Character.isDigit(ch))
    number += Character.digit(ch, 10);
// END isdigit
else
    System.out.println("5 correct");
int radix = 16;
// START maxmin
if (radix >= Character.MIN_RADIX &&
    radix <= Character.MAX_RADIX)
    number = Character.digit(ch, radix);
// END maxmin
     }
 }

class spaces {
// START space
// Returns the number of white space characters in char array
static int countWhitespaces(char[] str) {
    int count = 0;
    for (int i = 0; i < str.length; i++)
	if (Character.isWhitespace(str[i]))
	        ++count;
    return (count);
}
// END space
    public static char test() {
	char[] str1 = {'a', 'b', ' ', '\t'};
	System.out.println("Answer should be 2: " 
			   + countWhitespaces(str1));
	return ('a');
    }
}

class cases {
    public static char test() {
char ch = 'A';
// START islower
if (Character.isLowerCase(ch))
    return (Character.toUpperCase(ch));
// END islower
System.out.println("6 correct");

char[] name = {'R', 'o', 's', 'e'};
// START tolower
// Make all characters in the char array 'name' lowercase
for (int i = 0; i < name.length; i++)
    name[i] = Character.toLowerCase(name[i]);
// END tolower
System.out.println("lowercase: " + name[0]+name[1]+name[2]+name[3]);
// START toupper
// Make all characters in the char array 'name' uppercase
for (int i = 0; i < name.length; i++)
    name[i] = Character.toUpperCase(name[i]);
// END toupper
System.out.println("uppercase: " + name[0]+name[1]+name[2]+name[3]);

// START isupper
if (Character.isUpperCase(ch))
    return (Character.toLowerCase(ch));
// END isupper
return 'a';
    }
}
class misc {
    public static int test() {
// START main
Character charobj = new Character('A');
char[] str = new char[3];
// UNCOM ...
str[2] = charobj.charValue();
// UNCOM ...
// UNCOMif (charobj.equals(anotherObject))
// UNCOM    return 0;
// END main
System.out.println(charobj.toString());
return 0;
    }
}


class Main {
    public static void main(String args[]) {
// START const
Character newch = new Character('A');
// END const
// START str
Character ch = new Character('A');
String chstr = ch.toString();
System.out.println("Value of ch is " + chstr);
// END str
	digits.test();
	spaces.test();
	cases.test();
	eq1.test();
	maxmin.test();
misc.test();
    }
}
