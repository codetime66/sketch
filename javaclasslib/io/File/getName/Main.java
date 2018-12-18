import java.io.File;

class Main {
    public static void main(String[] args) {
System.out.println(new File("Main.java").getName());        // Main.java
System.out.println(
    new File("c:\\frontpage_webs\\", "Content").getName()); // Contents
System.out.println(new File("c:").getName()); 		    // c:
System.out.println(new File("\\tmp\\graphics").getName());  // graphics
System.out.println(new File("\\tmp\\graphics\\").getName());// (empty string)
    }
}
