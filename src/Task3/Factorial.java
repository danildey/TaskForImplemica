package Task3;

import java.math.BigInteger;
import static java.math.BigInteger.ONE;
/**
 * Class is based on the balanced tree algorithm
 */

public class Factorial {
    private static final int MAX = 200;//Default cache size
    private BigInteger[] cache = new BigInteger[MAX+1];
    private BigInteger N = BigInteger.valueOf(MAX+1);
    private final BigInteger TWO = ONE.add(ONE);
    /**
     * Default (internal) constructor constructs our cache.
     */
    public Factorial() {
        BigInteger b = ONE;
        cache[0] = b;
        cache[1] = b;
        for (int i = 2; i <= MAX; ++i) {
            b = b.multiply(BigInteger.valueOf(i));
            cache[i] = b;
        }
    }
    /**
     * Task3.Factorial calculation method.
     * The method  handle negative numbers, adjusts the cache size, and calculates the factorial.
     */
    public BigInteger calc(int x) {
        if (x < 0) throw new IllegalArgumentException("negative x");
        if (x <= MAX) return cache[x];
        BigInteger M = BigInteger.valueOf(x);
        return cache[MAX].multiply(multiply_range(N, M));
    }
    /**
     * This method divides the numbers into multiplication ranges by the tree principle.
     */
    public BigInteger multiply_range(BigInteger n, BigInteger m) {
        if (n.equals(m)) return n;
        if (m.compareTo(n) < 1) return ONE;
        BigInteger half = n.add(m).divide(TWO);
        BigInteger next = half.add(ONE);
        return multiply_range(n, half).multiply(multiply_range(next, m));
    }
    /**
     * Calculates sum of the digits in the number.
     * */
    public int sum(BigInteger factorial){
        BigInteger sum = BigInteger.valueOf(0);
        while (!factorial.equals(BigInteger.ZERO)) {
            sum = sum.add(factorial.mod(BigInteger.valueOf(10)));
            factorial = factorial.divide(BigInteger.valueOf(10));
//                System.out.println( ""+ factorial);
        }
        return sum.intValue();
    }
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        Factorial factorial = new Factorial();

        System.out.println(factorial.sum(factorial.calc(100)));

//        System.out.println(System.currentTimeMillis()-start + " ms");

    }
}