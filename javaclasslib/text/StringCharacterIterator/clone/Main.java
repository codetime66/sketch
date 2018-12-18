import java.text.StringCharacterIterator;

class Main {

    public static void main(String args[]) {

        String str = "Hello world";

        // create string character iterator
        StringCharacterIterator iter = new StringCharacterIterator(str, 6);
        printValues(iter);         // prints '6' and 'w'

        // create a clone 
        StringCharacterIterator iterCopy = 
            (StringCharacterIterator)iter.clone(); 
        printValues(iterCopy);     // prints '6' and 'w'

        if(iter.equals(iterCopy)) {
            System.out.println("Copy is equal to original");
        } else {
            System.out.println("Copy is not equal to original");
        }

        // compute hashcode
        int hc = iter.hashCode();
        System.out.println("Hash code is: " + hc);
    }

    public static void printValues(StringCharacterIterator it) {
        System.out.println(it.getIndex());
        System.out.println(it.current());
    }
}
