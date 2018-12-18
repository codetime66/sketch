import java.util.*;

class Main {
    public static void main(String[] args) {
        Locale NORWAY = new Locale("no","NO");
        Locale NORWAY_NYNORSK = new Locale("no","NO","NY");

        System.out.println(NORWAY.getDisplayName());
            // Norwegian (Bokmal) (Norway)
        System.out.println(NORWAY.getDisplayVariant());
            // 

        System.out.println(NORWAY_NYNORSK.getDisplayName());
            // Norwegian (Nynorsk) (Norway,NY)
        System.out.println(NORWAY_NYNORSK.getDisplayVariant());
            // NY
    }
}