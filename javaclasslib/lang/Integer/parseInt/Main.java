class Main {
    public static void main(String[] args) {
        try {
            int i_dec = Integer.parseInt("25");          // decimal
            int i_oct = Integer.parseInt("65", 8);       // octal
            int i_hex1 = Integer.parseInt("1f", 16);     // hex
            int i_hex2 = Integer.parseInt("1e", 16);     // hex
            int i_oct2 = Integer.parseInt("033");        // leading 0 ignored
            // int i_hex3 = Integer.parseInt("0x1e");    // ERROR: format
            // int i_hex4 = Integer.parseInt("0x1e", 16);// ERROR: format

            System.out.println("parsed: " + i_dec + "," + i_oct + "," + i_hex1
                               + ","  + i_hex2 + "," + i_oct2);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
