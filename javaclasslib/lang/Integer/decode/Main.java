class Main {
    public static void main(String[] args) {
        try {
            Integer i_dec = Integer.decode("225");
            Integer i_oct = Integer.decode("065");       // octal
            Integer i_hex1 = Integer.decode("0x1f");     // hex
            Integer i_hex2 = Integer.decode("#1e");      // hex
            // Integer i_big = Integer.decode("1234123455555");// ERROR: too big

            System.out.println("parsed: " + 
                i_dec + "," + i_oct + "," + i_hex1 + ","  + i_hex2);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
