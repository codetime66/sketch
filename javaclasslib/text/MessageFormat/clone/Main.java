import java.text.MessageFormat;

class Main {
    public static void main(String args[]) {

        // create message format
        MessageFormat df = new MessageFormat("The disk {1} contains {0}."); 
        System.out.println("Original pattern: " + df.toPattern());

        // create a clone 
        MessageFormat dfCopy = (MessageFormat)df.clone(); 
        System.out.println("Copy's pattern:   " + df.toPattern());

        // tests for equality
        if (df.equals(dfCopy)) {
            System.out.println("Clone is equal to original");
        }

        // compute hashcode
        int hc = df.hashCode();
        System.out.println("Hash code:        " + hc);
    }
}
