import java.net.*;

class Main {
    public static void main(String[] args) {
        try {
            URL u = new URL("http://java.sun.com/new.html#_top_");
            System.out.println(u.equals(new URL(u.toString()))); // true
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
