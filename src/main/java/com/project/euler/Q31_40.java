package com.project.euler;

import com.project.euler.utils.Utility;

import java.util.HashSet;
import java.util.Set;

public class Q31_40 {
    public static void main(String[] args) {
        System.out.println("Q31: " + question31());
        System.out.println("Q32: " + question32());
        System.out.println("Q33: " + question33());
        System.out.println("Q34: " + question34());
        System.out.println("Q35: " + question35());
        System.out.println("Q36: " + question36());
        System.out.println("Q37: " + question37());
        System.out.println("Q38: " + question38());
        System.out.println("Q39: " + question39());
        System.out.println("Q40: " + question40());
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

    private static long question32() {
        Set<Integer> nums = new HashSet<>();
        for(int i=1; i<=9; i++) {
            for(int j =9876; j>=1234; j--) {
                if(Utility.isPanDigital(i*j + "" + i + j, 9))
                    nums.add(i * j);
            }
        }

        for(int i=12; i<=98; i++) {
            for(int j =987; j>=123; j--) {
                if(Utility.isPanDigital(i*j + "" + i + "" + j, 9))
                    nums.add(i * j);
            }
        }

        return nums.stream().mapToInt(i->i).sum();
    }

    private static int question33() {
        int a = 1, b = 1;
        for(int i=11; i<=49; i++) {
            for(int n=i+1; n<=99; n++) {
                if(i%10 == 0) continue;
                String num = String.valueOf(i);
                String den = String.valueOf(n);
                float temp;

                if(num.toCharArray()[0] == den.toCharArray()[0] && den.toCharArray()[1]!=0) {
                    temp = Character.getNumericValue(num.toCharArray()[1]) / (float) Character.getNumericValue(den.toCharArray()[1]);
                    if(temp == (i/(float) n)) {
                        a*=i;
                        b*=n;
                    }

                } else if(num.toCharArray()[0] == den.toCharArray()[1]) {
                    temp = Character.getNumericValue(num.toCharArray()[1]) / (float) Character.getNumericValue(den.toCharArray()[0]);
                    if(temp == (i/(float) n)) {
                        a*=i;
                        b*=n;
                    }

                } else if(num.toCharArray()[1] == den.toCharArray()[0] && den.toCharArray()[1]!=0) {
                    temp = Character.getNumericValue(num.toCharArray()[0]) / (float) Character.getNumericValue(den.toCharArray()[1]);
                    if(temp == (i/(float) n)) {
                        a*=i;
                        b*=n;
                    }

                } else if(num.toCharArray()[1] == den.toCharArray()[1]) {
                    temp = Character.getNumericValue(num.toCharArray()[0]) / (float) Character.getNumericValue(den.toCharArray()[0]);
                    if(temp == (i/(float) n)) {
                        a*=i;
                        b*=n;
                    }
                }
            }
        }

        // numbers are : 16/64, 19/95, 26/65, 49/98
        return 100;
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
            if(Utility.isPanDigital(num, 9)) {
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

    private static int question40() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        while (sb.length() <= 1000000) {
            sb.append(i++);
        }

        return Character.getNumericValue(sb.charAt(0)) *
                Character.getNumericValue(sb.charAt(9)) *
                Character.getNumericValue(sb.charAt(99)) *
                Character.getNumericValue(sb.charAt(999)) *
                Character.getNumericValue(sb.charAt(9999)) *
                Character.getNumericValue(sb.charAt(99999)) *
                Character.getNumericValue(sb.charAt(999999));
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
