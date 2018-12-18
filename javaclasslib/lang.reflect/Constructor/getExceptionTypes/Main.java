import java.lang.reflect.*;
import java.util.NoSuchElementException;


class Main {
    public static void main(String[] args) {
        Constructor[] cons = ClassX.class.getConstructors();

        // Scan each method.
        for (int i=0; i<cons.length; i++) {
            Class[] es = cons[i].getExceptionTypes();
            
            // Print out each exception
            for (int j=0; j<es.length; j++) {
                System.out.println(es[j]);
            }
        }
    }
}

class ClassX {
    public ClassX() throws NoSuchElementException, IllegalArgumentException {
    }
}
