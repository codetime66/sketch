import java.util.Random;

class Main {
    public static void main(String args[]) {
        Random rand = new Random();

        byte[] b1 = new byte[1];
        rand.nextBytes(b1);
        System.out.println(b1[0]);

        byte[] b2 = new byte[2];
        rand.nextBytes(b2);
        System.out.println(b2[0] + " " + b2[1]);

        byte[] b3 = new byte[3];
        rand.nextBytes(b3);
        System.out.println(b3[0] + " " + b3[1] + " " + b3[2]);

        byte[] b10 = new byte[10];
        rand.nextBytes(b10);
        for (int i = 0; i < b10.length; i++) {
            System.out.print(b10[i] + " ");
        }
        System.out.println("\n");

        byte[] b50 = new byte[50];
        rand.nextBytes(b50);
        for (int i = 0; i < b50.length; i++) {
            System.out.print(b50[i] + " ");
        }
    }
}
