package com.project.euler;

import com.project.euler.utils.ContinuedFractionBig;
import com.project.euler.utils.Convergents;
import com.project.euler.utils.Utility;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Q61_70 {
    public static void main(String[] args) throws IOException {
        Q61_70 q61_70 = new Q61_70();

        System.out.println("Problem 62: " + problem62());
        System.out.println("Problem 63: " + problem63());
        System.out.println("Problem 64: " + problem64());
        System.out.println("Problem 65: " + problem65());
        System.out.println("Problem 67: " + q61_70.problem67());
        System.out.println("Problem 69: " + problem69());
    }

    // check Q61_70.py
    private static long problem62() {
        return 127035954683L;
    }

    /*
     * for n^k to be of k digits, k = log(10) to base (10/n)
     * */
    private static int problem63() {
        int counter = 0;
        for(int n=1; n<=9; n++) {
            counter += Math.floor(Math.log(10) / Math.log(10.0 / n));
        }
        return counter;
    }

    public static int problem64() {
        long start = System.currentTimeMillis()/1000;
        int oddConv = 0;
        for(int j=2; j<=10000; j++) {
            List<Integer> cf;
            try {
                cf = ContinuedFractionBig.continuedFractionSqrtBig(j);
                if(cf.size()%2==0) oddConv++;
            } catch(IllegalArgumentException ignored) { }
        }

        System.out.println(System.currentTimeMillis()/1000 - start + " sec time taken");
        return oddConv;
    }

    private static int problem65() {
        int EXTRA = 5;
        int NTERMS = 100;

        // Returns an array list of size NTERMS+1
        List<Integer> cf = Convergents.continuedFractionE(NTERMS+EXTRA);
        BigInteger[] conv = Convergents.convergents(cf,NTERMS);

        return Utility.sumDigits(conv[0].toString());
    }

    private int problem67() throws IOException {
        String list = new Utility().readFromInputStream("p067_triangle.txt");
        List<List<Integer>> nums = new ArrayList<>();
        for(String line: list.split("\n")) {
            nums.add(Arrays.stream(line.split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList()));
        }

        for(int i=nums.size()-2; i>=0; i--) {
            List<Integer> integers = nums.get(i);
            for(int j=0; j<=i; j++) {
                int temp = integers.get(j) + Math.max(nums.get(i+1).get(j), nums.get(i+1).get(j+1));
                integers.set(j, temp);
            }
            nums.set(i, integers);
        }

        return nums.get(0).get(0);
    }

    private static int problem69() {
        int n = 1, k = 0;
        int[] primes = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 21, 23, 29, 31};
        while (primes[k] * n <= 1000000)
            n*= primes[k++];
        return n;
    }
}
