package com.project.euler.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/** Continued fraction representation.
 *
 * This program finds the sequence of integers
 * that show up in the continued fraction
 * representation of a square root.
 */
public class ContinuedFractionBig {

    public static boolean isPerfectSquare(int x) {
        int s = (int)(Math.round(Math.sqrt(x)));
        return x==(s*s);
    }

    /**
     * Find the (really long) continued fraction sequence for sqrt(n).
     * The BigDouble is needed if you have more than about 10 terms,
     * since the very very tiny rounded portion of the remainder
     * becomes important for longer sequences.
     */
    public static List<Integer> continuedFractionSqrtBig(int n) {
        if(isPerfectSquare(n)) {
            throw new IllegalArgumentException("No continued fraction representation for perfect squares.");
        }

        int niters = 300; // handbrake
        int SCALE = 300; // decimal places

        int ai;
        List<Integer> coeffs = new ArrayList<>();
        MathContext MATH_CTX = new MathContext(SCALE, RoundingMode.FLOOR);//HALF_UP);
        BigDecimal val;

        BigDecimal remainder = BigDecimal.ONE;
        remainder = remainder.divide( BigDecimal.valueOf(n), MATH_CTX);
        remainder = sqrtBig(remainder, SCALE);

        for(int i=0; i<niters; i++) {
            val = BigDecimal.ONE;
            val = val.divide(remainder, MATH_CTX);
            ai = (int)(Math.floor(val.doubleValue()));
            remainder = val.subtract( BigDecimal.valueOf(ai) );
            coeffs.add(ai);
            if(coeffs.get(i) == 2*coeffs.get(0)) {
                break;
            }
        }
        return coeffs;
    }

    /** Find the square root of a BigDecimal to precision SCALE. */
    public static BigDecimal sqrtBig(BigDecimal A, final int SCALE) {
        BigDecimal x0 = new BigDecimal("0");
        BigDecimal x1 = BigDecimal.valueOf(Math.sqrt(A.doubleValue()));
        while (!x0.equals(x1)) {
            x0 = x1;
            x1 = A.divide(x0, SCALE, RoundingMode.HALF_UP);
            x1 = x1.add(x0);
            x1 = x1.divide(BigDecimal.valueOf(2), SCALE, RoundingMode.HALF_UP);

        }
        return x1;
    }

}