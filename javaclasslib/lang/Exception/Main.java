
// START const
import java.util.Date;

class PrinterOutOfPaperException extends Exception {
    public Date when;
    public PrinterOutOfPaperException() {
	super();
	when = new Date();
    }
    public PrinterOutOfPaperException(String msg) {
	super(msg);
	when = new Date();
    }
}

// COM
class printer {
    public static void test() {
// COM
try {
// UNCOM  ...
    throw new PrinterOutOfPaperException();
} catch (PrinterOutOfPaperException e) {
    System.err.println("Out of paper since " + e.when);
// UNCOM  ... <wait for paper and continue>
}

try {
// UNCOM  ...
    throw new PrinterOutOfPaperException("ATTENTION");
} catch (PrinterOutOfPaperException e) {
    System.err.println("Out of paper since " + e.when);
// UNCOM  ... <wait for paper and continue>
}
// END const
    }
}


    

// START main
// attempting to add to stack when it is full
class StackOverflow extends Exception {
};

// attempting to access stack when it is empty
class StackUnderflow extends Exception {
};

// A stack that has a 3 item limit
class threeDeep {
    static final int STACK_SIZE = 3;
    private int[] stack_store = new int[STACK_SIZE];
    private int stack_ptr = 0;

    // push item onto stack
    public void push(int item) throws StackOverflow {
	if (stack_ptr >= STACK_SIZE)
	    throw new StackOverflow();
	else 
	    stack_store[stack_ptr++] = item;
    }

    // pop item off top of stack
    public int pop() throws StackUnderflow {
	if (stack_ptr == 0)
	    throw new StackUnderflow();
	else
	    return (stack_store[--stack_ptr]);
    }
// COM
    public boolean stack_full_p()  { 
	return (stack_ptr >= STACK_SIZE);
    }
    public boolean stack_empty_p() {
	return (stack_ptr == 0);
    }
// COM
}

// COM
class Main {
    public static void main (String[] args) {
// COM
threeDeep s = new threeDeep();
// COM
    for (int i = 0; true; i++) {
// COM
try {
    s.push(i);
// COM
    System.err.println("push " + i);
// COM
} catch (StackOverflow e) {
    System.err.println("overflow " + i);
// COM
    break;
// COM
}
// COM
    }
    while(true) {
// UNCOM  ...
// COM
try {
    System.out.println("pop " + s.pop());
} catch (StackUnderflow e) {
    System.err.println("underflow " + e);
// COM
	    break;
// COM
}
// END main
    }

printer.test();
}
}
