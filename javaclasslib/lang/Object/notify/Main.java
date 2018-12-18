// A stack that has a 3 item limit
class Stack {
    static final int STACK_SIZE = 3;
    private int[] stack_store = new int[STACK_SIZE];
    private int stack_ptr = 0;

    // push item onto stack
    // If stack is full, wait until it has room
    synchronized public void push(int item) {
        while (stack_ptr >= STACK_SIZE) {
            try {
                wait();
            } catch (InterruptedException e) {
                // ignore
            }
        }
        if (stack_ptr == 0)
            notify();   // pop was awaiting stack to fill
        stack_store[stack_ptr++] = item;
    }

    // pop item off top of stack
    // If stack is empty, wait until it has item
    synchronized public int pop() {
        while (stack_ptr == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                // ignore
            }
        }
        if (stack_ptr >= STACK_SIZE) 
            notify();  // push was awaiting stack to drain
        return(stack_store[--stack_ptr]);
    }
}

// Thread that loops, pushing items onto the stack, and then
// sleeping a random period of time
class Stacker extends Thread {
    Stack s;
    Stacker(Stack s) {
        super();
        this.s = s;
    }
    public void run() {
        while (true) {
            int rand = Math.round((float)((Math.random()* 6)));
            s.push(rand);
            System.out.println("push: " + rand);
            try {
                Thread.sleep(Math.round(Math.random()*100));
            } catch (InterruptedException e) {
            }
        }
    }
}

// Thread that loops, popping an item off the stack, and then
// sleeping a random period of time 
class Retriever extends Thread {
    Stack s;
    Retriever(Stack s) {
        super();
        this.s = s;
    }
    public void run() {
        while (true) {
            int top = s.pop();
            System.out.println("pop: " + top);
            try {
                Thread.sleep(Math.round(Math.random()*100));
            } catch (InterruptedException e) {
            }
        }
    }
}

class Main {
    public static void main(String[] args) {
        Stack s = new Stack();  // create stack

        // create threads
        Thread rthread = new Retriever(s); 
        Thread sthread = new Stacker(s);

        // start threads
        sthread.start();
        rthread.start();
    }
}
