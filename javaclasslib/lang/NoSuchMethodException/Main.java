class Main {
    public static void main(String[] args) {
        System.out.println("NoSuchMethodException example");
        try {
            Class c = Class.forName("A");
            Object a = c.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
