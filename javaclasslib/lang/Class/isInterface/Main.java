class Main {
    public static void main(String[] args) {
        System.out.println(Main.class.isInterface());                // false
        System.out.println(java.lang.Cloneable.class.isInterface()); // true
        System.out.println(Main.InnerI.class.isInterface());         // true
        System.out.println(int.class.isInterface());                 // false
    }

    interface InnerI {}
}
