package Task3;

import java.math.BigInteger;
import static java.math.BigInteger.ONE;
/**
 * The factorial is calculated using the balanced tree algorithm
 */

public class Factorial {
    private static final int MAX = 200;//Default cache size
    private BigInteger[] cache = new BigInteger[MAX+1];
    private BigInteger N = BigInteger.valueOf(MAX+1);
    private final BigInteger TWO = ONE.add(ONE);

    public Factorial() {
        createCache();
    }

    public void createCache() {
        BigInteger b = ONE;
        cache[0] = b;
        cache[1] = b;
        for (int i = 2; i <= MAX; ++i) {
            b = b.multiply(BigInteger.valueOf(i));
            cache[i] = b;
        }
    }

    public BigInteger calc(int x) {
        if (x < 0) throw new IllegalArgumentException("negative x");
        if (x <= MAX) return cache[x];
        BigInteger M = BigInteger.valueOf(x);
        return cache[MAX].multiply(multiply_range(N, M));
    }

    /*
     * This method divides the numbers into multiplication ranges by the tree principle.
     */
    public BigInteger multiply_range(BigInteger n, BigInteger m) {
        if (n.equals(m)) return n;
        if (m.compareTo(n) < 1) return ONE;
        BigInteger half = n.add(m).divide(TWO);
        BigInteger next = half.add(ONE);
        return multiply_range(n, half).multiply(multiply_range(next, m));
    }

    public int sumOfTheDigitsInTheNumber(BigInteger factorial) {
        BigInteger sum = BigInteger.valueOf(0);
        while (!factorial.equals(BigInteger.ZERO)) {
            sum = sum.add(factorial.mod(BigInteger.valueOf(10)));
            factorial = factorial.divide(BigInteger.valueOf(10));
        }
        return sum.intValue();
    }
    public static void main(String[] args) {
        Factorial factorial = new Factorial();
        System.out.println(factorial.sumOfTheDigitsInTheNumber(factorial.calc(100)));
    }
}