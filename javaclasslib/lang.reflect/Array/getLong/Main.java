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

        // The set of array types on which getLong() can operate.
        Array.getLong(bytes, 0);
        Array.getLong(shorts, 0);
        Array.getLong(chars, 0);
        Array.getLong(ints, 0);
        Array.getLong(longs, 0);

        // The set of array types on which setLong() can operate.
        Array.setLong(longs, 0, 1L);
        Array.setLong(floats, 0, 1L);
        Array.setLong(doubles, 0, 1L);
    }
}
