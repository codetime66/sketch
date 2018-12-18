class Main {
    public static void main(String[] args) {
        String str = new String("num\u00e9ro");

        try {
            byte[] def = str.getBytes();
            byte[] utf = str.getBytes("UTF8");

            // print out contents of byte arrays
            for (int i = 0; i < def.length; i++) {
                System.out.println("[" + i + "]:" + def[i]);
            }
            System.out.println("-----");
            for (int i = 0; i < utf.length; i++) {
                System.out.println("[" + i + "]:" + utf[i]);
            }
            System.out.println("-----");

            // Reconstruct strings using byte arrays
            String defstr = new String(def);
            String utfstr = new String(utf, "UTF8");

            System.out.println("default: " + defstr);
            System.out.println("utf8: " + utfstr);

        } catch (java.io.UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
