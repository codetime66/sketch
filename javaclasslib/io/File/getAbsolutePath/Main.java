import java.io.File;

class Main {
    public static void main(String[] args) {
System.out.println(new File("Main.java").getAbsolutePath()); 
					// e:\book\egs\io\File\Main.java
System.out.println(
    new File("c:\\frontpage_webs\\", "Content").getAbsolutePath()); 
					// c:\frontpage_webs\Content
System.out.println(
    new File("c:\\frontpage_webs", "Content").getAbsolutePath()); 
					// c:\frontpage_webs\Content
System.out.println(new File("\\tmp\\graphics").getAbsolutePath()); 
					// \tmp\graphics
    }
}
