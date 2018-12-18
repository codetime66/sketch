class Main {
    private static void storeItem(Object[]a, int i, Object item) {
        a[i] = item;
    }
    public static void main(String[] args) {
        System.out.println("ArrayStoreException Example");

        Integer[] a = new Integer[3];
        storeItem(a, 2, new String("abc"));
    }
}
