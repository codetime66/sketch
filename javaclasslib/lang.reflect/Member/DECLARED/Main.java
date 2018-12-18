import java.lang.reflect.*;

class Main {
    static void check(Class c) {
        SecurityManager sm = System.getSecurityManager();

        // Check public access.
        try {
            if (sm != null) {
                sm.checkMemberAccess(c, Member.PUBLIC);
            }
            System.out.print("You do ");
        } catch (SecurityException e) {
            System.out.print("You don't ");
        }
        System.out.println("have permission to access all public "
            + "inherited and declared fields, methods, and constructors "
            + "of " + c);

        // Check declared access.
        try {
            if (sm != null) {
                sm.checkMemberAccess(c, Member.DECLARED);
            }
            System.out.print("You do ");
        } catch (SecurityException e) {
            System.out.print("You don't ");
        }
        System.out.println("have permission to access all declared "
            + "fields, methods, and constructors "
            + "of " + c);

    }
    public static void main(String[] args) {
        // Access enabled.
        check(Main.class);

        System.setSecurityManager(new MySecurityManager());
        System.out.println();

        // Access disabled.
        check(Main.class);
    }
}

class MySecurityManager extends SecurityManager {
    public void checkMemberAccess(Class clazz, int which) {
        throw new SecurityException();
    }
}