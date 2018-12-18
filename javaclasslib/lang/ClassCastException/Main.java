class Main {
    private static void storeItem(Integer[]a, int i, Object item) {
        a[i] = (Integer) item;
    }
    public static void main(String[] args) {
        System.out.println("ClassCastException Example");

        Integer[] a = new Integer[3];
        storeItem(a, 2, new String("abc"));
    }
}
