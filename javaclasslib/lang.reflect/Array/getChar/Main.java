import java.lang.reflect.*;

class Main {
    public static void main(String[] args) {
        byte[] bytes = new byte[1];
        char[] chars = new char[1];
        short[] shorts = new short[1];
        int[] ints = new int[1];
        long[] longs = new long[1];
        float[] floats = new float[1];
        double[] doubles = new double[1];

        // The set of array types on which getChar() can operate.
        Array.getChar(chars, 0);

        // The set of array types on which setChar() can operate.
        Array.setChar(chars, 0, 'a');
        Array.setChar(ints, 0, 'a');
        Array.setChar(longs, 0, 'a');
        Array.setChar(floats, 0, 'a');
        Array.setChar(doubles, 0, 'a');
    }
}
