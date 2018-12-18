import java.io.File;

class Main {
    public static void main(String[] args) {
System.out.println(new File("Main.java").isFile());          // true
System.out.println(new File("c:").isFile());                 // false
System.out.println(new File("e:Main.java").isFile());        // true
System.out.println(new File("c:\\").isFile());               // false
System.out.println(new File("c:\\notthere").isFile());       // false
System.out.println(new File("c:\\tmp").isFile());            // false
    }
}
