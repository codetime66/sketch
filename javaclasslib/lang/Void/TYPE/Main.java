public class Main {
//
public static void main(String[] args) {
    Class c = Void.TYPE;
    System.out.println("TYPE: " + c);
    System.out.println("isPrimitive: " + c.isPrimitive());
    System.out.println("superclass: " + c.getSuperclass());
    try {
        Object obj = c.newInstance();  // ERROR
        System.out.println("obj: " + obj);
    } catch (InstantiationException e) {
        e.printStackTrace();
    } catch (IllegalAccessException e) {
        e.printStackTrace();
    }
}
//
}
