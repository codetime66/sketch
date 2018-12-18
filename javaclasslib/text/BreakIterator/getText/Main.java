import java.text.StringCharacterIterator;
import java.text.CharacterIterator;
import java.text.BreakIterator;

class Main {
    public static void main(String args[]) {

        String str = "Hello world.";

        // Create the word break iterator.
        BreakIterator wb = BreakIterator.getWordInstance();
        wb.setText(str);

        // Get the text from the boundary.
        CharacterIterator ci = wb.getText();

        // Print the text.
        printText(ci);

        // Now set the text using a string character iterator.
        // Notice the start and begin are set to pick up "bye"
        wb.setText(new StringCharacterIterator("Goodbye world.", 4, 7, 4));

        // Get the text from the boundary.
        ci = wb.getText();

        // Print the text again.
        printText(ci);
    }

    public static void printText(CharacterIterator ci){
        // print the entire text using the character iterator
        do {
            System.out.print(ci.current());
            ci.next();
        } while (ci.current() != CharacterIterator.DONE) ;
        System.out.println("");
    }
}
