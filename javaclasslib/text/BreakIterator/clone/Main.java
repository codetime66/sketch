import java.text.BreakIterator;

class Main {

    public static void main(String args[]) {

        String str = "Hello world";

        // create word break iterator
        BreakIterator wb = BreakIterator.getWordInstance();
        wb.setText(str);
        wb.next();
        printValues(wb);       // prints '5' and 'w'

        // create a clone 
        BreakIterator wbCopy = (BreakIterator)wb.clone(); 
        printValues(wbCopy);   // prints '5' and 'w'
    }

    public static void printValues(BreakIterator b) {
        System.out.println(b.current());
        System.out.println(b.getText().current());
    }
}
