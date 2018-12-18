class Main {
    public static void main(String[] args) {
        try {
            short s_dec = Short.parseShort("25");          // decimal
            short s_oct = Short.parseShort("65", 8);       // octal
            short s_hex1 = Short.parseShort("1f", 16);     // hex
            short s_hex2 = Short.parseShort("1e", 16);     // hex
            short s_oct2 = Short.parseShort("033");        // leading 0 ignored
            // short s_hex3 = Short.parseShort("0x1e");    // ERROR: format
            // short s_hex4 = Short.parseShort("0x1e", 16);// ERROR: format
            // short s_big = Short.parseShort("55555");    // ERROR: too big

            System.out.println("parsed: " + s_dec + "," + s_oct + "," + s_hex1
                               + ","  + s_hex2 + "," + s_oct2);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
