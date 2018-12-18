import java.text.StringCharacterIterator;

class Main {
    public static void main(String args[]) {

        String str =  "We want to thank you.";
        String str2 = "  |<-SUBRANGE-->|  ";

        // create string character iterator
        StringCharacterIterator sci = 
                               new StringCharacterIterator(str, 3, 17, 11);

        System.out.println("'sci' is the string character iterator ");
        System.out.println("'|' is positioned ahead of the current index");
        System.out.println("                      " + str2);

        char rtn = sci.current();
        System.out.print("sci.current():       ");
        printOutChar(sci, str, rtn);

        rtn = sci.next();
        System.out.print("sci.next():          ");
        printOutChar(sci, str, rtn);

        rtn = sci.previous();
        System.out.print("sci.previous():      ");
        printOutChar(sci, str, rtn);

        rtn = sci.setIndex(sci.getEndIndex() - 2);
        System.out.print("sci.setIndex(" + (sci.getEndIndex() - 2) + "):    ");
        printOutChar(sci, str, rtn);

        rtn = sci.next();
        System.out.print("sci.next():          ");
        printOutChar(sci, str, rtn);

        rtn = sci.next();
        System.out.print("sci.next():          ");
        printOutChar(sci, str, rtn);

        rtn = sci.first();
        System.out.print("sci.first():         ");
        printOutChar(sci, str, rtn);

        rtn = sci.last();
        System.out.print("sci.last():          ");
        printOutChar(sci, str, rtn);

        int i = sci.getIndex();
        System.out.print("sci.getIndex():      ");
        printOutInt(sci, str, i);

        i = sci.getBeginIndex();
        System.out.print("sci.getBeginIndex(): ");
        printOutInt(sci, str, i);

        i = sci.getEndIndex();
        System.out.print("sci.getEndIndex():   ");
        printOutInt(sci, str, i);
    }

    // Print the text with a separator '|' at the current position
    public static void printOutChar(StringCharacterIterator iter, 
                                    String str, char rtn) {
        printFirstPart(iter, str);
        if (rtn == StringCharacterIterator.DONE) 
            System.out.println("   returns " + "DONE");
        else
            System.out.println("   returns \"" + rtn + "\"");
    }

    // Print the text with a separator '|' at the current position
    public static void printOutInt(StringCharacterIterator iter, 
        String str, int rtn) {
        printFirstPart(iter, str);
        if (rtn == StringCharacterIterator.DONE) 
            System.out.println("   returns " + "DONE");
        else
            System.out.println("   returns \"" + rtn + "\"");
    }

  public static void printFirstPart(StringCharacterIterator iter, String str) {
        int pos = iter.getIndex();
        System.out.print("\"" + str.substring(0,pos));
        System.out.print("|");
        System.out.print(str.substring(pos,str.length()) + "\"");
  }
}
