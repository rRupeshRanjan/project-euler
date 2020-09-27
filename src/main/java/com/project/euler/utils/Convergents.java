package com.project.euler.utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/** Continued fraction representation.
 *
 * This program finds the sequence of integers
 * that show up in the continued fraction
 * representation of a square root.
 */
public class Convergents {

    public static List<Integer> continuedFractionSqrt2(int nterms) {
        List<Integer> cf = new ArrayList<>();
        cf.add(1);
        for(int i=1; i<nterms; i++) {
            cf.add(2);
        }
        return cf;
    }

    public static List<Integer> continuedFractionE(int nterms) {
        List<Integer> cf = new ArrayList<>();
        cf.add(2);

        int k = 1;
        for(int i=1; i<nterms; i++) {
            if((i+1)%3==0)
                cf.add(2*k++);
            else
                cf.add(1);
        }
        return cf;
    }

    public static BigInteger[] convergents(List<Integer> cfrepr, int nterms) {
        BigInteger[] convergents = new BigInteger[2];

        // Initial values for convergent recurrence relation
        BigInteger Pnm2 = BigInteger.ZERO; // P_{n-2}
        BigInteger Pnm1 = BigInteger.ONE;
        BigInteger Qnm2 = BigInteger.ONE;
        BigInteger Qnm1 = BigInteger.ZERO;
        BigInteger P = BigInteger.ZERO;
        BigInteger Q = BigInteger.ZERO;

        // Term 0 is the constant value a0.
        int accessindex = 0;
        for(int i=0; i<nterms; i++) {
            int an = cfrepr.get(accessindex);

            P = Pnm1.multiply( BigInteger.valueOf(an) ).add(Pnm2);
            Q = Qnm1.multiply( BigInteger.valueOf(an) ).add(Qnm2);

            Pnm2 = Pnm1;
            Pnm1 = P;

            Qnm2 = Qnm1;
            Qnm1 = Q;

            if(accessindex+1>=cfrepr.size()) {
                // Ensure we keep repeating the sequence
                // if the sequence has fewer terms than user asks for.
                // This allows us to get really good
                // approximations for numbers.
                // This only works because the sequence is palindromic.
                accessindex = 1;
            } else {
                accessindex++;
            }
        }

        convergents[0] = P;
        convergents[1] = Q;

        return convergents;
    }
}
