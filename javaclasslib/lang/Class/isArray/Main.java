class Main {
    static void printIsArray(Object o) {
        System.out.println(o.getClass().isArray());
    }

    public static void main(String[] args) {
        printIsArray(new String());          // false
        printIsArray(new String[0]);         // true
        printIsArray(new String[0][0]);      // true
        printIsArray(new int[0]);            // true
    }
}
