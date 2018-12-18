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

        // The set of array types on which getInt() can operate.
        Array.getInt(bytes, 0);
        Array.getInt(shorts, 0);
        Array.getInt(chars, 0);
        Array.getInt(ints, 0);

        // The set of array types on which setInt() can operate.
        Array.setInt(ints, 0, 1);
        Array.setInt(longs, 0, 1);
        Array.setInt(floats, 0, 1);
        Array.setInt(doubles, 0, 1);
    }
}
