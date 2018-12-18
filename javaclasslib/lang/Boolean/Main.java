class misc1 {
    public static int test() {
// START const
Boolean status = new Boolean(true);
// END const
System.out.println("correct 2");
return (0);
}
}


class misc2 {
    public static int test() {
// START val
Boolean status = new Boolean(false);
boolean bval = status.booleanValue();
if (bval)
    return (-1);
// END val
System.out.println("correct 3");

// START equals
Object obj1 = new Boolean(true);
Object obj2 = new Boolean(false);
if (obj1.equals(obj2))
    return (-1);
// END equals
System.out.println("correct 4");

// START true
if(status.equals(Boolean.TRUE))
    return (-1);
// END true
System.out.println("correct 7");

// START prop
if (Boolean.getBoolean("os.password.required")) {
// UNCOM   password = Login.getPassword("Password:");
} 
// END prop
System.out.println("correct 6");

// START false
if(status.equals(Boolean.FALSE))
    return (-1);
// END false
return(0);
}
}

class misc3 {
    public static int test() {
// START str
Boolean status = new Boolean(true);
String strval = status.toString();
System.out.println("Value of status is: " + strval);
// END str
return(0);
}
}


// START main
class Main {
    public static void main(String args[]) {
	Boolean tb = new Boolean(true);
	Boolean fb = new Boolean(false);

	if (tb.booleanValue() && fb.booleanValue())
	    System.err.println("logic error");

	if (tb.equals(Boolean.TRUE) && fb.equals(Boolean.FALSE))
	    System.err.println("expected behavior");
	
	System.out.println("tb :" + tb.toString());
	System.out.println("fb :" + fb.toString());
// COM
	System.out.println("correct 1");
	misc1.test();
	misc2.test();
	misc3.test();
// COM
    }
}
// END main


	    
