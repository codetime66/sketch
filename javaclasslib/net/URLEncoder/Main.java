import java.net.URLEncoder;

class Main {
    public static void main(String[] args) {
        String x500name = new String("c=us/o=sun/ou=eng");

        String u1 = URLEncoder.encode(x500name);
        System.out.println(u1);       // "c%3dus%2fo%3dsun%2fou%3deng"

        String u2 = URLEncoder.encode("Open Sesame");
        System.out.println(u2);       // "Open+Sesame"

    }
}
