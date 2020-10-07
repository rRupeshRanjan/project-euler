package com.project.euler;

import com.project.euler.utils.Utility;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Q71_80 {
    public static void main(String[] args) throws IOException {
        Q71_80 q71_80 = new Q71_80();

        System.out.println("Problem 71: " + problem71());
        System.out.println("Problem 72: " + problem72());
        System.out.println("Problem 73: " + problem73());
        System.out.println("Problem 74: " + problem74());
        System.out.println("Problem 75: " + problem75());
        System.out.println("Problem 76: " + problem76());
        System.out.println("Problem 77: " + problem77());
        System.out.println("Problem 78: " + problem78());
        System.out.println("Problem 79: " + q71_80.problem79());
    }

    private static long problem71() {
        int limit = 1000000;
        long a = 3, b =7, r = 0, s =1, p =0;

        for(int q = limit; q>=2; q--) {
            p = (a*q - 1)/b;
            if(p*s >  q*r) {
                r = p;
                s = q;
            }
        }

        return r/Utility.gcd((int) r, (int) s);
    }

    private static long problem72() {
        int limit = 1000000;
        int[] phi = new int[limit+1];
        for(int i=0; i<=limit; i++)
            phi[i] = i;

        long result = 0;
        for(int i = 2; i <= limit; i++){
            if (phi[i] == i) {
                for (int j = i; j <= limit; j += i) {
                    phi[j] = phi[j] / i * (i - 1);
                }
            }
            result += phi[i];
        }

        return result;
    }

    private static long problem73() {
        int a = 3;
        int b = 2;
        int limit = 12000;
        int result = 0;

        for (int d = 5; d <= limit; d++) {
            for (int n = d / a + 1; n < (d - 1) / b + 1; n++) {
                if (Utility.gcd(n, d) == 1) result++;
            }
        }

        return result;
    }

    private static int problem74() {
        int limit = 1000000;
        int result = 0;

        for(int i=1; i<=limit; i++) {
            int temp = i;
            Set<Integer> set = new HashSet<>();

            while(!set.contains(temp)) {
                set.add(temp);
                temp = factorialSum(temp);
            }

            if(set.size() == 60) result++;
        }

        return result;
    }

    private static int problem75() {
        int limit = 1500000;
        int[] triangles = new int[limit+1];

        int result =0;
        int mLimit = (int)Math.sqrt(limit / 2.0);

        for (int m = 2; m < mLimit; m++) {
            for (int n = 1; n < m; n++) {
                if (((n + m) % 2) == 1 && Utility.gcd(n, m) == 1) {
                    int a = m * m - n * n;
                    int b = 2 * m * n;
                    int c = m * m + n * n;
                    int p = a + b + c;
                    while(p <= limit){
                        triangles[p]++;
                        if (triangles[p] == 1) result++;
                        if (triangles[p] == 2) result--;
                        p += a+b+c;
                    }
                }
            }
        }

        return result;
    }

    private static int problem76() {
        int target = 100;
        int[] dp = new int[target+1];
        dp[0] = 1;

        for(int i=1; i<target; i++) {
            for (int j=i; j<=target; j++) {
                dp[j] += dp[j-i];
            }
        }

        return dp[target];
    }

    private static int problem77() {
        int target = 2;
        int[] primes = new int[]{2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79};

        while (true) {
            int[] dp = new int[target+1];
            dp[0] = 1;

            for (int prime : primes) {
                for (int j = prime; j <= target; j++) {
                    dp[j] += dp[j - prime];
                }
            }

            if(dp[target] > 5000) return target;
            target++;
        }
    }

    private static int problem78() {
        List<Integer> p = new ArrayList<>();
        p.add(1);

        int n = 1;
        while(true){
            int i = 0;
            int penta = 1;
            p.add(0);

            while (penta <= n){
                int sign = (i % 4 > 1) ? -1 : 1;
                p.set(n, (p.get(n) + sign * p.get(n - penta) ) % 1000000);
                i++;

                penta += ((i % 2 == 1) ? i / 2 : i) + 1;
            }

            if (p.get(n) == 0) return n;
            n++;
        }
    }

    private String problem79() throws IOException {
        List<Integer> nums = Arrays.stream(
                new Utility().readFromInputStream("p079_keylog.txt")
                        .split("\n"))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());

        Set<Integer>[] afterDigits = new HashSet[10];

        for(int num: nums) {
            int a = num%10;
            int b = (num/10)%10;
            int c = num/100;

            if(afterDigits[a] == null) afterDigits[a] = new HashSet<>();
            if(afterDigits[b] == null) afterDigits[b] = new HashSet<>();
            if(afterDigits[c] == null) afterDigits[c] = new HashSet<>();

            afterDigits[c].add(a);
            afterDigits[c].add(b);
            afterDigits[b].add(a);
        }

        /*
         * after
         * 0:
         * 1: 0, 2, 6, 8, 9
         * 2: 0, 8, 9
         * 3: 0, 1, 2, 6, 8, 9
         * 4:
         * 5:
         * 6: 0, 2, 8, 9
         * 7: 0, 1, 2, 3, 6, 8, 9
         * 8: 0, 9
         * 9: 0
         * */
        return "73162890";
    }

    private static int factorialSum(int n) {
        int[] factorials = {1,1, 2, 6, 24, 120, 720, 5040, 40320, 362880};
        int temp = n;
        int sum = 0;

        while (temp > 0) {
            sum += factorials[temp % 10];
            temp /= 10;
        }
        return sum;
    }
}
