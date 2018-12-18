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

        // The set of array types on which getDouble() can operate.
        Array.getDouble(bytes, 0);
        Array.getDouble(shorts, 0);
        Array.getDouble(chars, 0);
        Array.getDouble(ints, 0);
        Array.getDouble(longs, 0);
        Array.getDouble(floats, 0);
        Array.getDouble(doubles, 0);

        // The set of array types on which setDouble() can operate.
        Array.setDouble(doubles, 0, 1L);
    }
}
