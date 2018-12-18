import java.io.File;

class Main {
    public static void main(String[] args) {
System.out.println(new File("Main.java").isAbsolute());        // false
System.out.println(new File("c:").isAbsolute());               // false
System.out.println(new File("c:Main.java").isAbsolute());      // false
System.out.println(new File("c:/").isAbsolute());              // true
System.out.println(new File("c:\\").isAbsolute());             // true
System.out.println(new File("c:/tmp").isAbsolute());           // true
System.out.println(new File("c:\\tmp").isAbsolute());          // true
System.out.println(new File("/tmp").isAbsolute());             // true
System.out.println(new File("\\tmp").isAbsolute());            // true
System.out.println(new File("\\tmp\\graphics").isAbsolute());  // true
System.out.println(new File("\\tmp\\graphics\\").isAbsolute());// true
    }
}
