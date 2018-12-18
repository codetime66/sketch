import java.io.File;

class Main {
    public static void main(String[] args) {
System.out.println(new File("Main.java").getPath());        // Main.java
System.out.println(new File("c:").getPath());               // c:
System.out.println(new File("c:\\tmp").getPath());          // c:\tmp
System.out.println(new File("\\tmp").getPath());            // \tmp
System.out.println(new File("\\tmp\\graphics").getPath());  // \tmp\graphics
System.out.println(new File("\\tmp\\graphics\\").getPath());// \tmp\graphics\
    }
}
