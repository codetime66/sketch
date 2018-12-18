import java.io.File;

class Main {
    public static void main(String[] args) {
System.out.println(new File("Main.java").isDirectory());          // false
System.out.println(new File("c:").isDirectory());                 // false
System.out.println(new File("e:Main.java").isDirectory());        // false
System.out.println(new File("c:\\").isDirectory());               // true
System.out.println(new File("c:\\notthere").isDirectory());       // false
System.out.println(new File("\\tmp").isDirectory());              // true
    }
}
