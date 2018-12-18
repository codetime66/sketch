class Main {
    public static void method1() {
        method2();
    }
    public static void method2() {
        method1();
    }
    public static void main(String[] args) {
        System.out.println("StackOverflowError example");
        method1();
    }
}
