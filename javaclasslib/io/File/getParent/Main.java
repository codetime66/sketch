import java.io.File;

class Main {
    public static void main(String[] args) {
System.out.println(new File("Main.java").getParent());        // null
System.out.println(new File("c:").getParent());               // null
System.out.println(new File("c:\\").getParent());             // null
System.out.println(new File("c:\\tmp").getParent());          // c:\
System.out.println(new File("\\tmp").getParent());            // \
System.out.println(new File("\\tmp\\graphics").getParent());  // \tmp
System.out.println(new File("\\tmp\\graphics\\").getParent());// \tmp\graphics
    }
}
