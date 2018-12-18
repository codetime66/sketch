class Main {
    public static void main(String[] args) {
        System.out.println(String.class.isPrimitive());    // false
        System.out.println(int.class.isPrimitive());       // true
        System.out.println(int[].class.isPrimitive());     // false
        System.out.println(Integer.TYPE.isPrimitive());    // true
        System.out.println(Void.TYPE.isPrimitive());       // true

        System.out.println(int.class == Integer.TYPE);     // true
    }
}
