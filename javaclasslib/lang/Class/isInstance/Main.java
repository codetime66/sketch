class Main {
    static void printIsInstance(Object a, Object b) {
        // Prints b instanceof a.getClass().
        System.out.println(a.getClass().isInstance(b));
    }

    public static void main(String[] args) {
        C c = new C();
        D d = new D();

        printIsInstance(c, null);               // false
        printIsInstance(c, c);                  // true
        printIsInstance(c, d);                  // true
        printIsInstance(d, c);                  // false

        printIsInstance(new Object(), new int[1]);        // true
        printIsInstance(new int[1], new int[1]);          // true
        printIsInstance(new C[1], d);                     // false
        printIsInstance(new C[1], new D[1]);              // true
        printIsInstance(new C[1], new C[1][1]);           // false

        System.out.println(I.class.isInstance(c));        // false
        System.out.println(I.class.isInstance(d));        // true
        System.out.println(I.class.isInstance(new D[1])); // false
    }
}

class C {}
class D extends C {}
interface I {}
