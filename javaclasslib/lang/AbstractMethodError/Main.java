class A extends B {
    public void method2(int i) {
        System.out.println(i);
    }
}
class Main {
    public static void main(String[] args) {
        System.out.println("AbstractMethodError example");
        A a = new A();
        a.method1(0);
    }
}
