class A implements Cloneable {
    int a;
    public A(int i) {
        a = i;
    }
    public Object clone() throws CloneNotSupportedException {
        // Use default cloning of field-to-field copy
        return super.clone();
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("Cloneable example");
        A i = new A(10);

        try {
            A j = (A)(i.clone());
            System.out.println(j.a);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
