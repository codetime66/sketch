package pkg2;

class A {
    int a;
    A(int i) {
        a = i;
        System.out.println("A created");
    }
    int add5() {
        return a+=5;
    }
}
