class Main {
    public static void main(String[] args) {
        try {
            StringBuffer buf = (StringBuffer)StringBuffer.class.newInstance();
            buf.append("Java");
            System.out.println(buf);             // Java

            // No null constructor.
            //Boolean.class.newInstance();       // NoSuchMethodException

            // Constructor is private
            //Class.class.newInstance();         // IllegalAccessException

            // Abstract class
            //Number.class.newInstance();        // InstantiationException
        } catch (InstantiationException e) {
            System.out.println(e);
        } catch (IllegalAccessException e) {
            System.out.println(e);
        }
    }
}
