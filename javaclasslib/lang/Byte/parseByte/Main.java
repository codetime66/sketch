class Main {
    public static void main(String[] args) {
        try {
            byte b_dec = Byte.parseByte("25");          // decimal
            byte b_oct = Byte.parseByte("65", 8);       // octal
            byte b_hex1 = Byte.parseByte("1f", 16);     // hex
            byte b_hex2 = Byte.parseByte("1e", 16);     // hex
            byte b_oct2 = Byte.parseByte("033");        // leading 0 ignored
            // byte b_hex3 = Byte.parseByte("0x1e");    // ERROR: format
            // byte b_hex4 = Byte.parseByte("0x1e", 16);// ERROR: format

            System.out.println("parsed: " + b_dec + "," + b_oct + "," + b_hex1
                               + ","  + b_hex2 + "," + b_oct2);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
