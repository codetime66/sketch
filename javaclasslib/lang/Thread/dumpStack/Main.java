class Main {
    static void method(int i) {
        if (i == 10) {
            Thread.currentThread().dumpStack();
        } else {
            method(++i);
        }
    }

    public static void main(String args[]) {
        method(0);
    }
}
