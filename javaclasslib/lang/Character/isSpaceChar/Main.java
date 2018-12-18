class Main {
    public static void main(String[] args) {
System.out.println("Uspace: " + Character.isSpaceChar('\u0020')); // True
System.out.println("Uline: " + Character.isSpaceChar('\u2028'));  // True
System.out.println("Upara: " + Character.isSpaceChar('\u2029'));  // True
System.out.println("Ufigure: " + Character.isSpaceChar('\u2007'));

System.out.println("space: " + Character.isSpaceChar(' '));       // True
System.out.println("newline: " + Character.isSpaceChar('\n'));    // False
System.out.println("tab: " + Character.isSpaceChar('\t'));        // False
System.out.println("return: " + Character.isSpaceChar('\r'));     // False
System.out.println("form feed: " + Character.isSpaceChar('\f'));  // False
    }
}
			   
