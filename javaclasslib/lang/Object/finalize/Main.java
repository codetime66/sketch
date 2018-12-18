class Main {
    public static void main(String[] args) {
        if (args.length > 0) {
            // Turn on finalizer.
            System.runFinalizersOnExit(true);
        }
        new C();

        // Keep busy until user hits Ctrl-C.
        if (args.length > 1) {
            long prod = 1;
            for (int i = 1; i < 1000000000; i++) {
                prod *= i;
            }
        }
    }

    static class C {
        C() {
            System.out.println("C created");
        }

        protected void finalize() {
            System.out.println("C finalized");
        }
    }
}
