class Main {
    public static void main(String[] args) {
        try {
            Integer i_dec = Integer.valueOf("25");          // decimal
            Integer i_oct = Integer.valueOf("65", 8);       // octal
            Integer i_hex1 = Integer.valueOf("1f", 16);     // hex
            Integer i_hex2 = Integer.valueOf("1e", 16);     // hex
            Integer i_oct2 = Integer.valueOf("033");        // leading 0 ignored
            // Integer i_hex3 = Integer.valueOf("0x1e");    // ERROR: format
            // Integer i_hex4 = Integer.valueOf("0x1e", 16);// ERROR: format

            System.out.println("parsed: " + i_dec + "," + i_oct + "," + i_hex1
                               + ","  + i_hex2 + "," + i_oct2);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
