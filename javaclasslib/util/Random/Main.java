import java.util.Random;
class Roulette {
    Random generator = new Random();
    // Spin the wheel and return a string (00, 0, 1-36)
    String spin() {
        int rand = generator.nextInt();
        int num = Math.abs(rand % 38);

        switch (num) {
        case 37: return ("00");
        case 36: return ("0");
        default: return (Integer.toString(num + 1)); // 1- 36 inclusive
        }
    }
    // Use a new seed when we change dealer
    void changeDealer() {
        generator.setSeed(System.currentTimeMillis());
    }
}
class Main {
    public static void main(String[] args) {
        Roulette r = new Roulette();

        // Spin 20 times
        for (int i = 0; i < 20; i++)
            System.out.println(i + ": " + r.spin());

        // change dealer
        r.changeDealer();

            // Spin 20 times again
        for (int i = 0; i < 20; i++)
            System.out.println(i + ": " + r.spin());
    }
}
