package com.project.euler;

import com.project.euler.utils.Utility;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Q31_40 {
    public static void main(String[] args) {
        System.out.println("Q31: " + question31());
        System.out.println("Q34: " + question34());
        System.out.println("Q35: " + question35());
        System.out.println("Q36: " + question36());
        System.out.println("Q37: " + question37());
        System.out.println("Q38: " + question38());
        System.out.println("Q39: " + question39());
    }

    private static int question31() {
        int[] coins = new int[] {1,2,5,10,20,50,100,200};
        int amount = 200;

        int[] dp = new int[amount+1];
        dp[0] = 1;

        for(int coin : coins) {
            for(int j = 0; j <= amount; j++) {
                if(j >= coin)
                    dp[j] += dp[j - coin];
            }
        }

        return dp[amount];
    }

    private static long question34() {
        int[] factorials = new int[10];
        factorials[0] = 1;
        for(int i=1; i<10; i++)
            factorials[i] = i * factorials[i-1];

        long sum = 0;
        for(int i=10; i<=1499999; i++) {
            if(i==factorialsSum(i, factorials))
                sum += i;
        }

        return sum;
    }

    private static int question35() {
        int max_size = 999997;
        int count = 13;
        boolean[] primeNumbers = Utility.getPrimes(max_size);

        for(int i=100; i<max_size; i++) {
            if(primeNumbers[i] && arePermutationsPrime(i, primeNumbers)) {
                count++;
            }
        }

        return count;
    }

    private static long question36() {
        long sum = 0;

        for(int i=0; i<1000000; i++) {
            if(Utility.isPalindrome(i) && Utility.isStringPalindrome(Utility.convertToBinary(i)))
                sum += i;
        }

        return sum;
    }

    private static int question37() {
        int sum = 0, max = 739398;
        boolean[] primes = Utility.getPrimes(max);

        for(int i=23; i<max; i++) {
            if(primes[i] && truncatedPrime(i, primes))
                sum += i;
        }

        return sum;
    }

    private static long question38() {
        long result = Long.MIN_VALUE;
        for(int i=9487; i>=9234; i--) {
            int num = 100002 * i;
            if(Utility.isPanDigital(num)) {
                result = Math.max(result, num);
            }
        }

        return result;
    }

    private static int question39() {
        int maxCount = 0, maxP = 0;
        for(int p=2; p<=1000; p++) {
            int count = 0;
            for(int a=2; a<p/3; a++) {
                if((p*(p-2*a)) % (2*(p-a)) == 0)
                    count++;
            }
            if(count > maxCount) {
                maxCount = count;
                maxP = p;
            }
        }

        return maxP;
    }

    private static boolean truncatedPrime(int i, boolean[] primes) {
        StringBuilder sb = new StringBuilder(String.valueOf(i));
        if(sb.charAt(0) == '1' || i%10==1)
            return false;

        while(i > 0) {
            int rightRemoval = (sb.length() > 1) ?
                    Integer.parseInt(sb.substring(1)) :
                    Integer.parseInt(sb.substring(0));
            if(!primes[i] || !primes[i/10] || !primes[rightRemoval])
                return false;
            i/=10;
            sb.deleteCharAt(0);
        }

        return true;
    }

    private static boolean arePermutationsPrime(int num, boolean[] primeNumbers) {
        Set<Integer> permutations = new HashSet<>();
        permutations.add(num);

        StringBuilder sb = new StringBuilder(String.valueOf(num));
        for(int i=0; i<5; i++) {
            String s = sb.charAt(sb.length() - 1) + sb.substring(0, sb.length() - 1);
            sb = new StringBuilder(s);
            permutations.add(Integer.parseInt(s));
        }

        for(int p: permutations) {
            if(p > primeNumbers.length || !primeNumbers[p])
                return false;
        }

        return true;
    }

    private static int factorialsSum(int num, int[] factorials) {
        int sum = 0;
        while(num > 0) {
            sum += factorials[num%10];
            num /= 10;
        }

        return sum;
    }

}
