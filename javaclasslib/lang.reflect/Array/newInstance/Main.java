import java.lang.reflect.*;

class Main {
    public static void main(String[] args) {
        // Creates an int array with 0 elements.
        int[] d0 = {0};
        int[] a0 = (int[])Array.newInstance(int.class, d0);

        // Do the same, this time using just an integer.
        int[] b0 = (int[])Array.newInstance(int.class, 0);

        // Create a 10x10 array of strings.
        int[] d1 = {10, 10};
        String[][] a1 = (String[][])Array.newInstance(String.class, d1);

        // Create an array of 10 int arrays.
        int[] d2 = {10};
        int[][] a2 = (int[][])Array.newInstance(int[].class, d2);

        // Do the same, this time using just an integer.
        int[][] b2 = (int[][])Array.newInstance(int[].class, 10);

        // Create a 10x10 array of 10 int arrays.
        int[] d3 = {10, 10};
        int[][][] a3 = (int[][][])Array.newInstance(int[].class, d3);
    }
}
