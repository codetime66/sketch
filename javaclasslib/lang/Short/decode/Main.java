class Main {
    public static void main(String[] args) {
        try {
            Short s_dec = Short.decode("225");
            Short s_oct = Short.decode("065");       // octal
            Short s_hex1 = Short.decode("0x1f");     // hex
            Short s_hex2 = Short.decode("#1e");      // hex
            // Short s_big = Short.decode("55555");  // ERROR: too big

            System.out.println("parsed: " + 
                s_dec + "," + s_oct + "," + s_hex1 + ","  + s_hex2);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
