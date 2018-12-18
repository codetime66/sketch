class Main {
    public static void main(String[] args) {
        try {
            Byte b_dec = Byte.decode("25");
            Byte b_oct = Byte.decode("065");       // octal
            Byte b_hex1 = Byte.decode("0x1f");     // hex
            Byte b_hex2 = Byte.decode("#1e");      // hex
            // Byte b_big = Byte.decode("255");    // ERROR: too big

            System.out.println("parsed: " + 
                b_dec + "," + b_oct + "," + b_hex1 + ","  + b_hex2);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
