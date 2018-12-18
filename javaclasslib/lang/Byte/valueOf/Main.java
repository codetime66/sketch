class Main {
    public static void main(String[] args) {
        try {
            Byte b_dec = Byte.valueOf("25");          // decimal
            Byte b_oct = Byte.valueOf("65", 8);       // octal
            Byte b_hex1 = Byte.valueOf("1f", 16);     // hex
            Byte b_hex2 = Byte.valueOf("1e", 16);     // hex
            Byte b_oct2 = Byte.valueOf("033");        // leading 0 ignored
            // Byte b_hex3 = Byte.valueOf("0x1e");    // ERROR: format
            // Byte b_hex4 = Byte.valueOf("0x1e", 16);// ERROR: format

            System.out.println("parsed: " + b_dec + "," + b_oct + "," + b_hex1
                               + ","  + b_hex2 + "," + b_oct2);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
