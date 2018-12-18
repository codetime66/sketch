import java.text.BreakIterator;

class Main {
    public static void main(String args[]) {

        String str = "We want to thank you.";
    
        // create word-break iterator
        BreakIterator wb = BreakIterator.getWordInstance();
        wb.setText(str);

        // create line-break iterator
        BreakIterator lb = BreakIterator.getLineInstance();
        lb.setText(str);

        System.out.println("'wb' is word-break iterator");
        System.out.println("'lb' is line-break iterator");

        // initial current position
        int rtn = wb.current();
        System.out.print("wb.current():      ");
        printCurrentBoundary(wb, str, rtn);

        // next boundary after current position
        rtn = wb.next();
                System.out.print("wb.next():         ");
        printCurrentBoundary(wb, str, rtn);

        // move 2 word boundaries ahead
        rtn = wb.next(2);
        System.out.print("wb.next(2):        ");
        printCurrentBoundary(wb, str, rtn);

        // initial current position
        rtn = lb.current();
        System.out.print("lb.current():      ");
        printCurrentBoundary(lb, str, rtn);

        // move 4 line boundaries ahead
        rtn = lb.next(4);
        System.out.print("lb.next(4):        ");
        printCurrentBoundary(lb, str, rtn);

        // move one boundary ahead
        rtn = lb.next();
        System.out.print("lb.next():         ");
        printCurrentBoundary(lb, str, rtn);

        // move one boundary ahead
        rtn = lb.next();
        System.out.print("lb.next():         ");
        printCurrentBoundary(lb, str, rtn);

        // move to previous boundary
        rtn = lb.previous();
        System.out.print("lb.previous():     ");
        printCurrentBoundary(lb, str, rtn);

        // move 100 boundaries ahead
        rtn = lb.next(100);
        System.out.print("lb.next(100):      ");
        printCurrentBoundary(lb, str, rtn);

        // move to boundary following character position 8
        rtn = lb.following(8);
        System.out.print("lb.following(8):   ");
        printCurrentBoundary(lb, str, rtn);

        // move to the boundary following the next-to-last boundary
        int nextToLast = lb.last() - 1;
        rtn = lb.following(nextToLast);
        System.out.print("lb.following(" + nextToLast + "):  ");
        printCurrentBoundary(lb, str, rtn);

        // move to first boundary
        rtn = lb.first();
        System.out.print("lb.first():        ");
        printCurrentBoundary(lb, str, rtn);

        // move to last boundary
        rtn = lb.last();
        System.out.print("lb.last():         ");
        printCurrentBoundary(lb, str, rtn);
    }

    // Print the text with a separator '|' at the current position
    public static void printCurrentBoundary(BreakIterator b,
                                            String src, 
                                            int rtn) {
        int pos = b.current();
        System.out.print("\"" + src.substring(0,pos));
        System.out.print("|");
        System.out.print(src.substring(pos,src.length()) + "\"");
        if (rtn == BreakIterator.DONE) 
            System.out.println("   returns " + "DONE");
        else
            System.out.println("   returns \"" + rtn + "\"");
    }
}
