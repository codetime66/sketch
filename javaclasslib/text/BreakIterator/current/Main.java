import java.text.BreakIterator;

class Main {
    public static void main(String args[]) {

        String str = "Thank you very much.";
    
        // create word break iterator
        BreakIterator wb = BreakIterator.getWordInstance();
        wb.setText(str);

        // next boundary after current position
        int pos = wb.next();
        System.out.println(wb.current());  // prints 5
    }
}
