import java.util.Stack;
import java.util.Date;
import java.util.EmptyStackException;

class Newspaper {
    Date date;
    String publisher;
    Newspaper(String pub, Date d) {
        date = d;
        publisher = pub;
    }
    Newspaper(String pub) {
        this(pub, new Date()); // use today's date
    }
    public String toString() {
        return (publisher + " " + date);
    }
}
class Main {
    public static void main(String[] args) {
        Stack newspapers = new Stack();
        
        Newspaper NYT = new Newspaper("New York Times");
        Newspaper SJM = new Newspaper("San Jose Mercury News");
        Newspaper SFC = new Newspaper("San Francisco Chronicle");
        Newspaper WSJ = new Newspaper("Wall Street Journal");

        // push onto stack
        newspapers.push(NYT);
        newspapers.push(SJM);
        newspapers.push(SFC);

        int where = newspapers.search(SJM);
        System.out.println(SJM + " is at the " + where + " position");

        where = newspapers.search(WSJ);
        if (where > 0)
            System.out.println(WSJ + " is at the " + where + " position");
        else 
            System.out.println(WSJ + " is not on the stack");

        try {
            Newspaper top = (Newspaper) newspapers.peek();
            System.out.println("Top contains " + top);

            do {
                top = (Newspaper) newspapers.pop();
                System.out.println("Popped off " + top);
            } while (!newspapers.empty());

        } catch (EmptyStackException e) {
            System.out.println("Trying to go beyond bottom of stack");
        }
    }
}
