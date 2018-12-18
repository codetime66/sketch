import java.text.DateFormatSymbols;

class Main {

    public static void main(String args[]) {

        // Create a date format symbols.
        DateFormatSymbols dfs = new DateFormatSymbols();

        // Get the localized pattern.
        System.out.println(dfs.getLocalPatternChars());

        // Create a clone.
        DateFormatSymbols dfsCopy = (DateFormatSymbols)dfs.clone(); 

        // Get the the localized pattern from the clone.
        System.out.println(dfsCopy.getLocalPatternChars());

        if(dfs.equals(dfsCopy)) {
            System.out.println("Copy is equal to original");
        } else {
            System.out.println("Copy is not equal to original");
        }

        // Compute hashcode.
        int hc = dfs.hashCode();
        System.out.println("Hash code is: " + hc);
    }
}
