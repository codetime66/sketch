import java.util.BitSet;

class Main {
    public static void main(String[] args) {
        BitSet bs = new BitSet();
        System.out.println("Original size: " + bs.size());    // 64
        // Set bit at 65
        bs.set(65);
        System.out.println("New size: " + bs.size());         // 128
    }
}
