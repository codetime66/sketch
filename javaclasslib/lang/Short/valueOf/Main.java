class Main {
    public static void main(String[] args) {
        try {
            Short s_dec = Short.valueOf("25");          // decimal
            Short s_oct = Short.valueOf("65", 8);       // octal
            Short s_hex1 = Short.valueOf("1f", 16);     // hex
            Short s_hex2 = Short.valueOf("1e", 16);     // hex
            Short s_oct2 = Short.valueOf("033");        // leading 0 ignored
            // Short s_hex3 = Short.valueOf("0x1e");    // ERROR: format
            // Short s_hex4 = Short.valueOf("0x1e", 16);// ERROR: format
            // Short s_big = Short.valueOf("55555");	// ERROR: too big

            System.out.println("parsed: " + s_dec + "," + s_oct + "," + s_hex1
                               + ","  + s_hex2 + "," + s_oct2);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
