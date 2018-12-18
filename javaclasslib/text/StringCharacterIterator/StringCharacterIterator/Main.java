import java.text.StringCharacterIterator;

class Main {
    public static void main(String args[]) {

        String str = "Hello big world";

        // Use one-argument constructor
        StringCharacterIterator iter1 = 
            new StringCharacterIterator(str);
        printRange(iter1, str);

        // Use two-argument constructor
        StringCharacterIterator iter2 = 
            new StringCharacterIterator(str, 6);
        printRange(iter2, str);

        // Use three-argument constructor
        StringCharacterIterator iter3 = 
            new StringCharacterIterator(str, 6, 9, 6);
        printRange(iter3, str);
    }

    // Print the full range of the iterator
    // with a separator '|' at the current index
    public static void printRange(StringCharacterIterator iter, String src) {
        int pos = iter.getIndex();
        System.out.print(src.substring(iter.getBeginIndex(),pos));
        System.out.print("|");
        System.out.println(src.substring(pos,iter.getEndIndex()));
    }
}
