class A {
//    public int fieldA = 100;
    public int fieldB = 200;
}

class Main {
    public static void main(String[] args) {
        System.out.println("NoSuchFieldException example");
        try {
            Class c = Class.forName("A");
            Object a = c.newInstance();
            System.out.println(c.getField("fieldA"));
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
