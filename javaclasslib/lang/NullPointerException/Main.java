class Main {
    public static void test(Object obj) {
        System.out.println(obj.toString());
    }
    public static void main(String[] args) {
        System.out.println("NullPointerException example");
        test(null);
    }
}
