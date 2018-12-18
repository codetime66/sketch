import java.lang.reflect.*;

class Main {
    public static void main(String[] args) {
        char[] charArray = {'a', 'b', 'c', 'd'};
        randomizeArray(charArray);
        printArray(charArray);

        int[] intArray = {0, 1, 2, 3, 4};
        randomizeArray(intArray);
        printArray(intArray);

        String[] stringArray = {"A", "B", "C", "D", "E"};
        randomizeArray(stringArray);
        printArray(stringArray);
    }

    static void randomizeArray(Object a) {
        int len = Array.getLength(a);

        for (int i=0; i<len/2; i++) {
            int ix1 = (int)Math.floor(Math.random()*len);
            int ix2 = (int)Math.floor(Math.random()*len);
            Object o = Array.get(a, ix1);

            // Swap elements.
            Array.set(a, ix1, Array.get(a, ix2));
            Array.set(a, ix2, o);
        }
    }

    static void printArray(Object a) {
        for (int i=0; i<Array.getLength(a); i++) {
            System.out.print(Array.get(a, i) + " ");
        }
        System.out.println();
    }
}
