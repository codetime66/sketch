package pkg2;

public class A {
    int a;
    public A(int i) {
        a = i;
        System.out.println("A created");
    }
    public int add5() {
        return a+=5;
    }
}
