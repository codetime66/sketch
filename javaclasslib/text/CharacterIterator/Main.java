import java.text.CharacterIterator;

class StrCharacterIterator implements CharacterIterator {
    private String text;
    private int begin;
    private int end;
    private int pos;

    public StrCharacterIterator(String text) {
      // this(text, 0);

        if (text == null)
            throw new NullPointerException();
        this.text = text;

        this.begin = 0;
        this.end = text.length();
        this.pos = 0;
    }

    public char first() {
        pos = begin;
        return text.charAt(pos);
    }

    public char last() {
        pos = end - 1;
        return text.charAt(pos);
    }

    public char setIndex(int p) {
        if (p < begin || p >= end)
            throw new IllegalArgumentException("Invalid index");
        pos = p;
        return text.charAt(p);
    }

    public char current() {
        if (pos >= begin && pos < end) {
            return text.charAt(pos);
        }
        else {
            return DONE;
        }
    }

    public char next() {
        if (++pos < end) {
            return text.charAt(pos);
        }
        else {
            return DONE;
        }
    }

    public char previous() {
        if (pos > begin) {
            return text.charAt(--pos);
        }
        else {
            return DONE;
        }
    }

    public int getBeginIndex() {
        return begin;
    }

    // the index returned is the index of the
    // first character following the end of the text.
    public int getEndIndex() {
        return end;
    }


    public int getIndex() {
        return pos;
    }
 

    public Object clone() {
        try {
            StrCharacterIterator other
            = (StrCharacterIterator) super.clone();
                return other;
        }
        catch (CloneNotSupportedException e) {
                throw new InternalError();
        }
    }
}

class Main {
    public static void main(String args[]) {

        String str = "Hello world";

        StrCharacterIterator iter = new StrCharacterIterator(str);

        traverseForward(iter);             // Hello world
        traverseBackward(iter);            // dlrow olleH
        traverseOut(iter, 5);              //  world olleH
    }


    // Traverse the text forward, from start to end.
    public static void traverseForward(StrCharacterIterator iter) { 
        for(char c = iter.first(); 
            c != StrCharacterIterator.DONE; 
            c = iter.next()) { 
            System.out.print(c); 
        }
        System.out.println();
    }

    // Traverse the text backwards, from end to start.
    public static void traverseBackward(StrCharacterIterator iter) { 
        for(char c = iter.last(); 
            c != CharacterIterator.DONE; 
            c = iter.previous()) { 
            System.out.print(c); 
        }
        System.out.println();
    }

    // Traverse from a given position forward to end and then
    // from that same given position backward to start.
    public static void traverseOut(StrCharacterIterator iter, int pos) { 
        for (char c = iter.setIndex(pos); 
             c != CharacterIterator.DONE; 
             c = iter.next()) {
             System.out.print(c); 
        } 
        int end = iter.getIndex(); 
        for (char c = iter.setIndex(pos); 
             c != CharacterIterator.DONE; 
             c = iter.previous()) {
             System.out.print(c); 
        } 
    }
}
