import java.util.BitSet;

class Main {
    private static void initBitSets(BitSet e, BitSet f) {
        for(int i = 0; i < 10; i++)
            e.set(i);
        for (int i = 5; i <15; i++)
            f.set(i);
        System.out.println("english: " + e);
        System.out.println("french: " + f);
    }
    public static void main(String[] args) {
        BitSet english = new BitSet(100);  // tracks English speakers
        BitSet french = new BitSet(100);   // tracks French speakers

        // initialize bit arrays with data
        initBitSets(english, french);

        BitSet bilingual = (BitSet)english.clone();
        bilingual.and(french);

        if (bilingual.equals(english))
            // this means entire English class is bilingual
            System.out.println("Completely bilingual class");
        else 
            System.out.println("Bilingual: " + bilingual.toString());

        BitSet either = (BitSet)english.clone();
        either.or(french);
        int eitherCount = 0;

        // count how many speak either English or French or both
        for (int i=0; i < either.size(); i++) {
            if (either.get(i))
                ++eitherCount;
        }
        System.out.println("Either(" + eitherCount + "):" + either);

        // find those who speak just either French or English but not both
        BitSet one = (BitSet)english.clone();
        one.xor(french);
        System.out.println("One: " + one);

        // Another ways to do this is to take 'either' and
        // eliminate those who are bilingual

        // fast way
        one = (BitSet)either.clone();
        one.xor(bilingual);
        System.out.println("One:" + one);

        // slow way
        one = (BitSet)either.clone();
        for (int i = 0; i < one.size(); i++)
            if (bilingual.get(i))
                one.clear(i);
        System.out.println("One:" + one);
    }
}
