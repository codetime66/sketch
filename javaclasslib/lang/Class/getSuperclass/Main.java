class Main {
    public static void main(String[] args) {
        System.out.println(java.lang.String.class.getSuperclass());
            // class java.lang.String
        System.out.println(java.awt.Button.class.getSuperclass());
            // class java.awt.Component

        System.out.println(java.awt.LayoutManager.class.getSuperclass());
            // null
        System.out.println(java.awt.LayoutManager2.class.getSuperclass());
            // null
    }
}