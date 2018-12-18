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

        // The set of array types on which getByte() can operate.
        Array.getByte(bytes, 0);

        // The set of array types on which setByte() can operate.
        Array.setByte(bytes, 0, (byte)1);
        Array.setByte(shorts, 0, (byte)1);
        Array.setByte(ints, 0, (byte)1);
        Array.setByte(longs, 0, (byte)1);
        Array.setByte(floats, 0, (byte)1);
        Array.setByte(doubles, 0, (byte)1);
    }
}
