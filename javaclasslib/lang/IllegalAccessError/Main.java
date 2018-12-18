import pkg2.A;

class Main {
    public static void main(String[] args) {
        System.out.println("IllegalAccessError example");
        A a = new A(10);
	System.out.println("a: " + a.add5());
    }
}
