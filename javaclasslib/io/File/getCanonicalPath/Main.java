import java.io.*;

class Main {
    public static void main(String[] args) {
try {
System.out.println(
    new File("Main.java").getCanonicalPath());	
						// /home/rosanna/tmp/Main.java
System.out.println(
    new File("~rosanna").getCanonicalPath());
						// /home/rosanna/tmp/~rosanna
System.out.println(
    new File("/export/home/tmp/../java", ".").getCanonicalPath());
						// /export/home/java
System.out.println(
    new File("/notthere/home/tmp/../java", ".").getCanonicalPath());
						// /notthere/home/tmp/../java/.
} catch (IOException e) {
    e.printStackTrace();
}
}
}
