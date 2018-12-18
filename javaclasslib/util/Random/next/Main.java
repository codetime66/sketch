import java.util.Random;

// Subclass that also has sequence generators for byte and short.
class NRandom extends Random {
    NRandom() {
    }

    NRandom(long seed) {
        super(seed);
    }

    public byte nextByte() {
        return (byte)next(8);   // Random low-order 8 bits
    }

    public short nextShort() {  // Random low-order 16 bits
        return (short)next(16);
    }
}

class Main {
    public static void main(String[] args) {
        NRandom rand = new NRandom(1997);

        // Let's see what the byte sequence looks like.
        for (int i = 0; i < 20; i++) {
            System.out.print(rand.nextByte() + " ");
        }
        System.out.println("\n");

        // Reset seed and try the short sequence.
        rand.setSeed(1997);
        for (int i = 0; i < 20; i++) {
            System.out.print(rand.nextShort() + " ");
        }
        System.out.println("\n");
    }
}
