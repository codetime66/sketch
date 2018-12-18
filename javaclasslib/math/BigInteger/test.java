import java.math.*;

class test {
    public static void main(String[] args) {

        BigInteger f1 = BigInteger.valueOf(1);
        BigInteger f2 = BigInteger.valueOf(1);
        BigInteger sum = BigInteger.valueOf(1);

        // Print first two Fibonacci nubmers.
	System.out.println(1);
	System.out.println(1);
        while (true) {
            sum = f1.add(f2);
            f1 = f2;
            f2 = sum;
            System.out.println(sum);
        }
    }
/*
        BigDecimal one = BigDecimal.valueOf(1).setScale(50);
        BigDecimal sum = BigDecimal.valueOf(0);
        int count = 0;

        for (int i=2; ; i+=2) {
            if (count % 2 == 0) {
                sum = sum.add(one.divide(BigDecimal.valueOf(i*(i+1)*(i+2)), 
                    BigDecimal.ROUND_DOWN));
            } else {
                sum = sum.subtract(one.divide(BigDecimal.valueOf(i*(i+1)*(i+2)), 
                    BigDecimal.ROUND_DOWN));
            }
            if (count % 1000 == 0) {
                System.out.println(sum.multiply(
                    BigDecimal.valueOf(4)).add(BigDecimal.valueOf(3)));
            }
            count++;
        }
    }
*/


/*
        BigDecimal n = BigDecimal.valueOf(4).setScale(50);
        BigDecimal d = BigDecimal.valueOf(1).setScale(50);

        for (int i=2; ; i+=2) {
            BigDecimal bi = BigDecimal.valueOf(i*(i+2));
            BigDecimal bj = BigDecimal.valueOf((i+1)*(i+1));
            n = n.multiply(bi);
            d = d.multiply(bj);
            if (i % 1000 == 0) {
                n = n.divide(d, BigDecimal.ROUND_DOWN);
                System.out.println(n);
                d = BigDecimal.valueOf(1);
            }
        }
    }
*/
}